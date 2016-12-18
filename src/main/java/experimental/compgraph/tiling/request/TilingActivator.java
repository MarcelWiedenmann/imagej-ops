
package experimental.compgraph.tiling.request;

import net.imglib2.FinalInterval;
import net.imglib2.Interval;
import net.imglib2.util.IntervalIndexer;

import experimental.compgraph.tiling.Tile;

public final class TilingActivator {

	private final TilingBulkRequestable<?, ?> target;

	public TilingActivator(final TilingBulkRequestable<?, ?> target) {
		this.target = target;
	}

	// Global coords
	public void request(final Interval interval) {
		target.request(interval);
	}

	// Local coords
	public void request(final Tile tile) {
		target.request(tile);
	}

	// see below
	public Interval request(final Tile tile, final int... overlap) {
		final long[] longOverlap = new long[overlap.length];
		for (int i = 0; i < overlap.length; i++) {
			longOverlap[i] = overlap[i];
		}
		return request(tile, longOverlap);
	}

	// input is "relative to local"
	// output should be global: TODO: this is a hack, we should make a Tile
	// provide both local and global coords
	// (by referencing an enclosing tiling that provides grid-dims and
	// tile-dims)
	public Interval request(final Tile tile, final long... overlap) {
		final int n = tile.numDimensions();
		final long[] globalMin = new long[n];
		final long[] globalMax = new long[n];

		int[] tileDims = target.getTileDims();

		long[] gridPos = new long[n];
		IntervalIndexer.indexToPosition(tile.flatIndex(), target.getGridDims(), gridPos);

		for (int d = 0; d < n; d++) {
			globalMin[d] = gridPos[d] * tileDims[d] - (d < overlap.length ? overlap[d] : 0);
			globalMax[d] = gridPos[d] * tileDims[d] + tileDims[d] + (d < overlap.length ? overlap[d] : 0);
		}
		final FinalInterval interval = new FinalInterval(globalMin, globalMax);
		target.request(interval);
		return interval;
	}
}
