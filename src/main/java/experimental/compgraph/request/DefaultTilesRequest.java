
package experimental.compgraph.request;

import java.util.Iterator;

import experimental.compgraph.tiling.Tile;
import experimental.compgraph.tiling.request.TilesRequest;

public class DefaultTilesRequest implements TilesRequest {

	private final Iterator<Tile> key;

	public DefaultTilesRequest(final Iterator<Tile> key) {
		this.key = key;
	}

	@Override
	public Iterator<Tile> key() {
		return key;
	}
}
