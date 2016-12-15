
package experimental.compgraph.request;

import net.imglib2.Interval;

public class DefaultTileRequest implements TileRequest {

	private final Interval key;

	public DefaultTileRequest(final Interval key) {
		this.key = key;
	}

	@Override
	public Interval key() {
		return key;
	}
}
