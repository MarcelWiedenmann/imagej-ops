
package experimental.compgraph.tiling;

import experimental.compgraph.AbstractCompgraphSinkNode;
import experimental.compgraph.CompgraphEdge;
import experimental.compgraph.request.TilingRequestable;

public class LocalTilingSink<IO> extends
	AbstractCompgraphSinkNode<LazyTile<IO>, TilingDataHandle<IO>, TilingRequestable<IO>>
{

	public LocalTilingSink(final CompgraphEdge<LazyTile<IO>> in) {
		super(in);
	}

	@Override
	protected TilingRequestable<IO> getInternal(final TilingDataHandle<IO> inData) {
		return inData.inner();
	}
}
