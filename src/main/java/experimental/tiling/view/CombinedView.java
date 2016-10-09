
package experimental.tiling.view;

import java.util.HashMap;
import java.util.Map;

import net.imglib2.AbstractInterval;
import net.imglib2.Interval;
import net.imglib2.Point;
import net.imglib2.RandomAccess;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.View;
import net.imglib2.view.Views;

public class CombinedView<T> extends AbstractInterval implements RandomAccessibleInterval<T>, View {

	private final RandomAccessibleInterval<RandomAccessibleInterval<T>> source;
	private final long[] blockSize;

	public CombinedView(final RandomAccessibleInterval<RandomAccessibleInterval<T>> source) {
		super(source.numDimensions());
		this.source = source;
		blockSize = new long[n];
		final RandomAccessibleInterval<T> block = source.randomAccess().get();
		for (int d = 0; d < n; ++d) {
			blockSize[d] = block.dimension(d);
			min[d] = block.min(d);
			max[d] = min[d] + blockSize[d] * source.dimension(d) - 1;
		}
	}

	public RandomAccessibleInterval<RandomAccessibleInterval<T>> getSource() {
		return source;
	}

	@Override
	public RandomAccess<T> randomAccess() {
		return new DefaultRA<>(source, blockSize);
	}

	@Override
	public RandomAccess<T> randomAccess(final Interval interval) {
		return randomAccess();
	}

	public static class DefaultRA<T> extends Point implements RandomAccess<T> {

		private final RandomAccessibleInterval<RandomAccessibleInterval<T>> source;
		private final RandomAccess<RandomAccessibleInterval<T>> sourceAccess;
		private final long[] blockSize;
		private final HashMap<Long, RandomAccess<T>> blockAccesses;
		final long[] index;
		final long[] offset;

		public DefaultRA(final RandomAccessibleInterval<RandomAccessibleInterval<T>> source, final long[] blockSize) {
			super(source.numDimensions());
			this.source = source;
			sourceAccess = source.randomAccess();
			// TODO: [Review] Passing blockSize needed? blocksAccess.get().dimensions is the same..
			this.blockSize = blockSize;
			// TODO: [Review] Determine HashMap size?
			blockAccesses = new HashMap<Long, RandomAccess<T>>();
			index = new long[n];
			offset = new long[n];
		}

		private DefaultRA(final DefaultRA<T> a) {
			super(a.position, true);
			source = a.source;
			sourceAccess = a.sourceAccess.copyRandomAccess();
			blockSize = a.blockSize;
			blockAccesses = new HashMap<Long, RandomAccess<T>>(a.blockAccesses.size());
			for (final Map.Entry<Long, RandomAccess<T>> entry : a.blockAccesses.entrySet()) {
				blockAccesses.put(entry.getKey(), entry.getValue().copyRandomAccess());
			}
			index = a.index.clone();
			offset = a.offset.clone();
		}

		@Override
		public T get() {
			long flatIndex = 0;
			for (int d = n - 1; d >= 0; --d) {
				final long normalizedPosition = position[d] - source.min(d);
				index[d] = normalizedPosition / blockSize[d];
				offset[d] = normalizedPosition % blockSize[d];
				flatIndex = flatIndex * source.dimension(d) + index[d];
			}
			final RandomAccess<T> blockAccess;
			if (blockAccesses.containsKey(flatIndex)) {
				blockAccess = blockAccesses.get(flatIndex);
			}
			else {
				sourceAccess.setPosition(index);
				// TODO: [Review] blockAccess.setPosition(offset) etc. assumes zeroMin. Other way to handle this?
				blockAccess = Views.zeroMin(sourceAccess.get()).randomAccess();
				blockAccesses.put(flatIndex, blockAccess);
			}
			blockAccess.setPosition(offset);
			return blockAccess.get();
		}

		@Override
		public DefaultRA<T> copy() {
			return new DefaultRA<T>(this);
		}

		@Override
		public DefaultRA<T> copyRandomAccess() {
			return copy();
		}
	}
}
