
package experimental.compgraph.tiling;

import java.util.Map;

import net.imglib2.AbstractEuclideanSpace;
import net.imglib2.Interval;
import net.imglib2.Point;
import net.imglib2.RandomAccess;
import net.imglib2.RandomAccessible;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.util.IntervalIndexer;

public class TilingMask<O> extends AbstractEuclideanSpace implements RandomAccessible<RandomAccessibleInterval<O>> {

	private final int[] tileSize;
	private final long[] gridDims;
	private final Map<Long, LazyTile<O>> source;

	public TilingMask(final Map<Long, LazyTile<O>> source, final long[] gridDims, final int[] tileSize) {
		super(gridDims.length);
		this.source = source;
		this.tileSize = tileSize;
		this.gridDims = gridDims;
	}

	@Override
	public RandomAccess<RandomAccessibleInterval<O>> randomAccess() {
		return new TilingMaskRandomAccess<>(source, gridDims, tileSize);
	}

	@Override
	public RandomAccess<RandomAccessibleInterval<O>> randomAccess(final Interval interval) {
		return randomAccess();
	}

	// -- Nested Classes --

	private static class TilingMaskRandomAccess<O> extends Point implements RandomAccess<RandomAccessibleInterval<O>> {

		private final long[] gridDims;
		private final int[] tileDims;
		private Map<Long, LazyTile<O>> source;

		public TilingMaskRandomAccess(final Map<Long, LazyTile<O>> source, final long[] gridDims,
				final int[] tileDims) {
			super(gridDims.length);
			this.source = source;
			this.gridDims = gridDims;
			this.tileDims = tileDims;
		}

		public TilingMaskRandomAccess(final TilingMaskRandomAccess<O> ra) {
			this.gridDims = ra.gridDims;
			this.tileDims = ra.tileDims;
		}

		@Override
		public RandomAccessibleInterval<O> get() {
			return source.get(IntervalIndexer.positionToIndex(position, gridDims));
		}

		@Override
		public TilingMaskRandomAccess<O> copy() {
			return new TilingMaskRandomAccess<>(this);
		}

		@Override
		public TilingMaskRandomAccess<O> copyRandomAccess() {
			return copy();
		}
	}
}
