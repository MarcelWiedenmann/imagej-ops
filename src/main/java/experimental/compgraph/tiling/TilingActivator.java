
package experimental.compgraph.tiling;

import java.util.HashMap;

import experimental.compgraph.request.Tile;

public class TilingActivator {

	private final TilingMask mask;

	private HashMap<Long, Tile> tiles;

	public TilingActivator(final TilingMask mask) {
		this.mask = mask;
	}

	public void request(final Tile tile) {
		request(tile, 0);
	}

	public void request(final Tile tile, final long... overlap) {
		// TODO: do complex and efficient stuff and occasionally call mark(..)
	}

	private void mark(final Tile tile) {
		tiles.put(tile.flatIndex(), tile);
		mask.mark(tile);
	}
}
