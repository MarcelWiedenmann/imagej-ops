
package experimental.tiling.view;

import net.imglib2.AbstractInterval;
import net.imglib2.Interval;
import net.imglib2.Point;
import net.imglib2.RandomAccess;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.View;
import net.imglib2.view.Views;

public class TiledView<T> extends AbstractInterval implements RandomAccessibleInterval<RandomAccessibleInterval<T>>,
	View
{

	// TODO: [Review] Utility method needed? (+ ok in this form? redundant calculations: blocksperdim --> blocksize -->
	// max)
	public static <T> TiledView<T> createFromBlocksPerDim(final RandomAccessibleInterval<T> source,
		final long[] blocksPerDim)
	{
		final long[] blockSize = new long[blocksPerDim.length];
		for (int d = 0; d < blockSize.length; ++d) {
			blockSize[d] = (source.dimension(d) - 1) / blocksPerDim[d] + 1;
		}
		return new TiledView<>(source, blockSize);
	}

	private final RandomAccessibleInterval<T> source;
	private final long[] blockSize;

	public TiledView(final RandomAccessibleInterval<T> source, final long[] blockSize) {
		super(source.numDimensions());
		this.source = source;
		this.blockSize = blockSize;
		for (int d = 0; d < n; ++d) {
			// TODO: [Review] "max" currently numbers tiles not pixels. Ok? (Should be ok...we are rai of rais)
			max[d] = (source.dimension(d) - 1) / blockSize[d];
		}
	}

	public RandomAccessibleInterval<T> getSource() {
		return source;
	}

	@Override
	public RandomAccess<RandomAccessibleInterval<T>> randomAccess() {
		return new DefaultRA<>(source, blockSize);
	}

	@Override
	public RandomAccess<RandomAccessibleInterval<T>> randomAccess(final Interval interval) {
		return randomAccess();
	}

	public static class DefaultRA<T> extends Point implements RandomAccess<RandomAccessibleInterval<T>> {

		private final RandomAccessibleInterval<T> source;
		private final long[] blockSize;
		private final long[] min;
		private final long[] max;

		public DefaultRA(final RandomAccessibleInterval<T> source, final long[] blockSize) {
			super(source.numDimensions());
			this.source = source;
			this.blockSize = blockSize;
			min = new long[n];
			max = new long[n];
		}

		private DefaultRA(final DefaultRA<T> a) {
			super(a.position, true);
			source = a.source;
			blockSize = a.blockSize;
			min = a.min.clone();
			max = a.max.clone();
		}

		@Override
		public RandomAccessibleInterval<T> get() {
			for (int d = 0; d < n; ++d) {
				min[d] = position[d] * blockSize[d];
				max[d] = min[d] + blockSize[d] - 1;
			}
			// TODO: [Review] Return updateable pseudo-neighborhood here?
			return Views.interval(source, min, max);
		}

		@Override
		public DefaultRA<T> copy() {
			return new DefaultRA<>(this);
		}

		@Override
		public DefaultRA<T> copyRandomAccess() {
			return copy();
		}
	}
}
