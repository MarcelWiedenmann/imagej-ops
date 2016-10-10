
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
			min[d] = source.min(d);
			max[d] = source.max(d);
		}
	}

	public RandomAccessibleInterval<RandomAccessibleInterval<T>> getSource() {
		return source;
	}

	@Override
	public CombinedViewRandomAccess<T> randomAccess() {
		return new CombinedViewRandomAccess<>(source, blockSize);
	}

	@Override
	public CombinedViewRandomAccess<T> randomAccess(final Interval interval) {
		return randomAccess();
	}

	public static class CombinedViewRandomAccess<T> extends Point implements RandomAccess<T> {

		private final RandomAccessibleInterval<RandomAccessibleInterval<T>> source;
		private final RandomAccess<RandomAccessibleInterval<T>> sourceAccess;
		private final long[] blockSize;
		private final HashMap<Long, RandomAccess<T>> blockAccesses;
		final long[] tempIndex;
		final long[] tempOffset;

		public CombinedViewRandomAccess(final RandomAccessibleInterval<RandomAccessibleInterval<T>> source,
			final long[] blockSize)
		{
			super(source.numDimensions());
			this.source = source;
			sourceAccess = source.randomAccess();
			this.blockSize = blockSize;
			// TODO: [Review] Determine HashMap size?
			blockAccesses = new HashMap<Long, RandomAccess<T>>();
			tempIndex = new long[n];
			tempOffset = new long[n];
		}

		private CombinedViewRandomAccess(final CombinedViewRandomAccess<T> ra) {
			super(ra.position, true);
			source = ra.source;
			sourceAccess = ra.sourceAccess.copyRandomAccess();
			blockSize = ra.blockSize;
			blockAccesses = new HashMap<Long, RandomAccess<T>>(ra.blockAccesses.size());
			for (final Map.Entry<Long, RandomAccess<T>> entry : ra.blockAccesses.entrySet()) {
				blockAccesses.put(entry.getKey(), entry.getValue().copyRandomAccess());
			}
			tempIndex = ra.tempIndex.clone();
			tempOffset = ra.tempOffset.clone();
		}

		@Override
		public T get() {
			long flatIndex = 0;
			for (int d = n - 1; d >= 0; --d) {
				// TODO: [Review] Needed for non-zeroMin source - ensure zeroMin somewhere else (view ctor)?
				final long normalizedPosition = position[d] - source.min(d);
				tempIndex[d] = normalizedPosition / blockSize[d];
				tempOffset[d] = normalizedPosition % blockSize[d];
				flatIndex = flatIndex * source.dimension(d) + tempIndex[d];
			}
			final RandomAccess<T> blockAccess;
			if (blockAccesses.containsKey(flatIndex)) {
				blockAccess = blockAccesses.get(flatIndex);
			}
			else {
				sourceAccess.setPosition(tempIndex);
				// TODO: [Review] Needed as blockAccess.setPosition(offset) etc. assume zeroMin. Other way to handle this?
				blockAccess = Views.zeroMin(sourceAccess.get()).randomAccess();
				blockAccesses.put(flatIndex, blockAccess);
			}
			blockAccess.setPosition(tempOffset);
			return blockAccess.get();
		}

		@Override
		public CombinedViewRandomAccess<T> copy() {
			return new CombinedViewRandomAccess<T>(this);
		}

		@Override
		public CombinedViewRandomAccess<T> copyRandomAccess() {
			return copy();
		}
	}
}
