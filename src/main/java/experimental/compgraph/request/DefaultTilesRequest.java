
package experimental.compgraph.request;

import java.util.List;

public class DefaultTilesRequest implements TilesRequest {

	private final List<Tile> key;

	public DefaultTilesRequest(final List<Tile> key) {
		this.key = key;
	}

	@Override
	public List<Tile> key() {
		return key;
	}
}
