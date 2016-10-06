
package experimental.tiling.view;

import java.util.HashMap;
import java.util.Map;

import net.imglib2.AbstractInterval;
import net.imglib2.Interval;
import net.imglib2.Point;
import net.imglib2.RandomAccess;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.View;

/**
 * @param <T>
 * @author Marcel Wiedenmann (University of Konstanz)
 */
public class CombinedView<T> extends AbstractInterval implements RandomAccessibleInterval<T>, View {

	private final RandomAccessibleInterval<RandomAccessibleInterval<T>> grid;
	private final GridIndexMapper mapper;

	public CombinedView(final RandomAccessibleInterval<RandomAccessibleInterval<T>> grid) {
		super(grid.numDimensions());
		this.grid = grid;
		final RandomAccessibleInterval<T> block = grid.randomAccess().get();
		for (int d = 0; d < n; ++d) {
			max[d] = block.dimension(d) * grid.dimension(d) - 1;
		}
		this.mapper = new GridIndexMapper();
	}

	public RandomAccessibleInterval<RandomAccessibleInterval<T>> getSource() {
		return grid;
	}

	// -- RandomAccessibleInterval --

	@Override
	public RandomAccess<T> randomAccess() {
		return new CombinedRandomAccess<T>(grid, mapper);
	}

	@Override
	public RandomAccess<T> randomAccess(final Interval interval) {
		return randomAccess();
	}

	public class CombinedRandomAccess<T> extends Point implements RandomAccess<T> {

		private final RandomAccessibleInterval<RandomAccessibleInterval<T>> grid;
		private final RandomAccess<RandomAccessibleInterval<T>> gridAccess;
		private final long[] blockSize;
		private final GridIndexMapper mapper;
		private final HashMap<Integer, RandomAccess<T>> blockAccesses;

		public CombinedRandomAccess(final RandomAccessibleInterval<RandomAccessibleInterval<T>> grid,
			final GridIndexMapper mapper)
		{
			super(grid.numDimensions());
			this.grid = grid;
			this.gridAccess = grid.randomAccess();
			this.mapper = mapper;
			// TODO: Determine HashMap size?
			this.blockAccesses = new HashMap<Integer, RandomAccess<T>>();
		}

		private CombinedRandomAccess(final CombinedRandomAccess<T> a) {
			super(a.position, true);
			grid = a.grid;
			gridAccess = a.gridAccess.copyRandomAccess();
			mapper = a.mapper;
			blockAccesses = new HashMap<Integer, RandomAccess<T>>(a.blockAccesses.size());
			for (final Map.Entry<Integer, RandomAccess<T>> entry : a.blockAccesses.entrySet()) {
				blockAccesses.put(entry.getKey(), entry.getValue().copyRandomAccess());
			}
		}

		@Override
		public T get() {
			final long[] index = new long[n];
			final long[] offset = new long[n];
			final long flatIndex = 0;
			for (int d = 0; d < n; d++) {
				index[d] = position[d] / blockSize[d];
				offset[d] = position[d] % blockSize[d];
			}

			final RandomAccess<T> blockAccess;
			if (blockAccesses.containsKey((int) flatIndex)) {
				blockAccess = blockAccesses.get(flatIndex);
			}
			else {
				gridAccess.setPosition(index);
				blockAccess = gridAccess.get().randomAccess();
				blockAccesses.put(flatIndex, blockAccess);
			}
			blockAccess.setPosition(offset);
			return blockAccess.get();
		}

		@Override
		public CombinedRandomAccess<T> copy() {
			return new CombinedRandomAccess<T>(this);
		}

		@Override
		public CombinedRandomAccess<T> copyRandomAccess() {
			return copy();
		}
	}
}
