package experimental.compgraph.tiling;

import experimental.compgraph.request.Tile;

// INTERNAL USAGE ONLY
public class TilingActivator {

	private final TilingBulkRequestable<?, ?> req;

	public TilingActivator(final TilingBulkRequestable<?, ?> req) {
		this.req = req;
	}

	public void request(final Tile tile) {
		request(tile, 0);
	}

	public void request(final Tile tile, final int... overlap) {
		final long[] longOverlap = new long[overlap.length];
		for (int i = 0; i < overlap.length; i++) {
			longOverlap[i] = overlap[i];
		}
		request(tile, longOverlap);
	}

	public void request(final Tile tile, final long... overlap) {
		// TODO: do complex and efficient stuff and occasionally call
		// mark(..)
		// ("Hey Mark, how you doin'?").
		// Translate to tiles.req;
	}

}
