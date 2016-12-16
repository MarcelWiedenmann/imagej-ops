
package experimental.compgraph.tiling;

import java.util.HashMap;

import experimental.compgraph.request.Tile;
import experimental.compgraph.request.TilingRequestable;

public class TilingActivator {

	private final TilingMask mask;

	private HashMap<Long, Tile> tiles;

	public TilingActivator(final TilingMask mask) {
		this.mask = mask;
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
		// TODO: do complex and efficient stuff and occasionally call mark(..) ("Hey Mark, how you doin'?")
	}

	private void mark(final Tile tile) {
		tiles.put(tile.flatIndex(), tile);
		mask.mark(tile);
	}

	public GridOfLazyTiles<I> createView(final TilingRequestable<I> requestable) {
		// TODO Auto-generated method stub
		return null;
	}
}
