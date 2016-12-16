
package experimental.compgraph.tiling;

import experimental.compgraph.DataHandle;
import experimental.compgraph.channel.collection.img.OpsTile;
import experimental.compgraph.tiling.request.TilingRequestable;

public class TilingDataHandle<IO> implements DataHandle<OpsTile<IO>, TilingRequestable<IO>> {

	private final TilingRequestable<IO> inner;

	public TilingDataHandle(final TilingRequestable<IO> inner) {
		this.inner = inner;
	}

	// -- DataHandle --

	@Override
	public TilingRequestable<IO> inner() {
		return inner;
	}
}
