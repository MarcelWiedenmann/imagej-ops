
package experimental.tiling.view;

import net.imglib2.AbstractInterval;
import net.imglib2.Interval;
import net.imglib2.Point;
import net.imglib2.RandomAccess;
import net.imglib2.RandomAccessible;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.View;
import net.imglib2.view.IntervalView;

public class TiledView<T> extends AbstractInterval implements RandomAccessibleInterval<RandomAccessibleInterval<T>>,
	View
{

	// TODO: [Review] Utility method ok in this form? (redundant calculations: blocksperdim --> blocksize --> max)
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

	// TODO: [Review] Allow source.numDimensions() != blockSize.length? (applies to other views, too)
	public TiledView(final RandomAccessibleInterval<T> source, final long... blockSize) {
		super(source.numDimensions());
		this.source = source;
		this.blockSize = blockSize;
		for (int d = 0; d < n; ++d) {
			max[d] = (source.dimension(d) - 1) / blockSize[d];
		}
	}

	public RandomAccessibleInterval<T> getSource() {
		return source;
	}

	@Override
	public TiledViewRandomAccess<T> randomAccess() {
		return new TiledViewRandomAccess<>(source, blockSize);
	}

	@Override
	public TiledViewRandomAccess<T> randomAccess(final Interval interval) {
		return randomAccess();
	}

	public static class TiledViewRandomAccess<T> extends Point implements RandomAccess<RandomAccessibleInterval<T>> {

		private final RandomAccessibleInterval<T> source;
		private final long[] blockSize;
		private final MutableIntervalView<T> tempBlock;
//		private final long[] tempMin;
//		private final long[] tempMax;

		public TiledViewRandomAccess(final RandomAccessibleInterval<T> source, final long[] blockSize) {
			super(source.numDimensions());
			this.source = source;
			this.blockSize = blockSize;
			this.tempBlock = new MutableIntervalView<>(source);
//			tempMin = new long[n];
//			tempMax = new long[n];
		}

		private TiledViewRandomAccess(final TiledViewRandomAccess<T> ra) {
			super(ra.position, true);
			source = ra.source;
			blockSize = ra.blockSize;
			tempBlock = ra.tempBlock.copy();
//			tempMin = ra.tempMin.clone();
//			tempMax = ra.tempMax.clone();
		}

		@Override
		public RandomAccessibleInterval<T> get() {
			for (int d = 0; d < n; ++d) {
//				tempMin[d] = position[d] * blockSize[d];
//				tempMax[d] = tempMin[d] + blockSize[d] - 1;
				final long min = position[d] * blockSize[d];
				tempBlock.setMin(min, d);
				tempBlock.setMax(min + blockSize[d] - 1, d);
			}
			// TODO: [Review] Return as zeroMin (same for ArrangedView)?
			// TODO: [Review] Modifying view corrupts previous references - do we really want that?
			return tempBlock;
//			return Views.interval(source, tempMin, tempMax);
		}

		@Override
		public TiledViewRandomAccess<T> copy() {
			return new TiledViewRandomAccess<>(this);
		}

		@Override
		public TiledViewRandomAccess<T> copyRandomAccess() {
			return copy();
		}
	}

	protected static final class MutableIntervalView<T> extends IntervalView<T> {

		public MutableIntervalView(final RandomAccessible<T> source, final long[] min, final long[] max) {
			super(source, min, max);
		}

		public MutableIntervalView(final RandomAccessibleInterval<T> source) {
			super(source, source);
		}

		private MutableIntervalView(final MutableIntervalView<T> view) {
			super(view.source, view.min.clone(), view.max.clone());
		}

		public void setMin(final long min, final int d) {
			this.min[d] = min;
		}

		public void setMax(final long max, final int d) {
			this.max[d] = max;
		}

		public MutableIntervalView<T> copy() {
			return new MutableIntervalView<T>(this);
		}
	}
}
