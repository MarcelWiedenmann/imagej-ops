
package experimental.compgraph.tiling.request;

import net.imglib2.FinalInterval;
import net.imglib2.Interval;

import experimental.compgraph.tiling.Tile;

public final class TilingActivator {

	private final TilingBulkRequestable<?, ?> target;

	public TilingActivator(final TilingBulkRequestable<?, ?> target) {
		this.target = target;
	}

	public void request(final Tile tile) {
		request(tile, 0);
	}

	public Interval request(final Tile tile, final int... overlap) {
		final long[] longOverlap = new long[overlap.length];
		for (int i = 0; i < overlap.length; i++) {
			longOverlap[i] = overlap[i];
		}
		return request(tile, longOverlap);
	}

	public Interval request(final Tile tile, final long... overlap) {
		final int n = tile.numDimensions();
		final long[] min = new long[n];
		final long[] max = new long[n];
		for (int d = 0; d < n; d++) {
			min[d] = d < overlap.length ? tile.min(d) - overlap[d] : tile.min(d);
			max[d] = d < overlap.length ? tile.max(d) + overlap[d] : tile.max(d);
		}
		final FinalInterval interval = new FinalInterval(min, max);
		target.request(interval);
		return interval;
	}
}
