
package experimental.compgraph.tiling;

import net.imglib2.AbstractEuclideanSpace;
import net.imglib2.Interval;
import net.imglib2.Point;
import net.imglib2.RandomAccess;
import net.imglib2.RandomAccessible;
import net.imglib2.util.IntervalIndexer;
import net.imglib2.view.experimental.CombinedView;

import experimental.compgraph.request.DefaultTile;
import experimental.compgraph.request.Tile;

public class TilingMask<I, O> extends AbstractEuclideanSpace implements RandomAccessible<Tile> {

	private final TilingBulkRequestable source;
	private final long[] tileSize;
	private final long[] gridDims;
	CombinedView<I> tiling;

	public TilingMask(final TilingBulkRequestable source, final long[] tileSize, final long[] gridDims) {
		super(gridDims.length);
		this.tileSize = tileSize;
		this.gridDims = gridDims;

		// TODO
		tiling = new CombinedView<>(null);
	}

	@Override
	public RandomAccess<Tile> randomAccess() {
		return new TilingMaskRandomAccess();
	}

	@Override
	public RandomAccess<Tile> randomAccess(final Interval interval) {
		return randomAccess();
	}

	// -- Nested Classes --

	public static class TilingMaskRandomAccess<O> extends Point implements RandomAccess<LazyTile<O>> {

		private final TilingBulkRequestable<O> tiles;
		private final long[] gridDims;
		private final long[] tileDims;

		public TilingMaskRandomAccess(final TilingBulkRequestable<O> tiles, final long[] gridDims, final long[] tileDims) {
			this.tiles = tiles;
			this.gridDims = gridDims;
			this.tileDims = tileDims;
		}

		public TilingMaskRandomAccess(final TilingMaskRandomAccess<O> ra) {
			this.tiles = ra.tiles;
			this.gridDims = ra.gridDims;
			this.tileDims = ra.tileDims;
		}

		public void stage() {
			tiles.request(new CompleteTile(position, gridDims, tileDims));
		}

		public void stagePartially(final long[] min, final long[] max) {
			final long i = IntervalIndexer.positionToIndex(position, gridDims);
			final long[] globalMin = new long[min.length];
			final long[] globalMax = new long[max.length];
			for (int d = 0; d < n; d++) {
				globalMin[d] = gridDims[d] * tileDims[d] + min[d];
				globalMax[d] = globalMin[d] + tileDims[d] - 1 + max[d];
			}
			tiles.request(new DefaultTile(globalMin, globalMax, i));
		}

		@Override
		public LazyTile<O> get() {
			stage();
			tiles.flush();
			return tiles.request(position);
		}

		@Override
		public TilingMaskRandomAccess<O> copy() {
			return new TilingMaskRandomAccess<>(this);
		}

		@Override
		public TilingMaskRandomAccess<O> copyRandomAccess() {
			return copy();
		}

		// -- Nested Classes --

		private static class CompleteTile implements Tile {
			// TODO
		}
	}
}
