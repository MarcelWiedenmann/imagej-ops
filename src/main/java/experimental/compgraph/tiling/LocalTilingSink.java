
package experimental.compgraph.tiling;

import net.imglib2.RandomAccessibleInterval;

import experimental.compgraph.AbstractCompgraphSinkNode;
import experimental.compgraph.CompgraphEdge;
import experimental.compgraph.request.TilingRequestable;

public class LocalTilingSink<IO> extends
	AbstractCompgraphSinkNode<RandomAccessibleInterval<IO>, TilingDataHandle<IO>, TilingRequestable<IO>>
{

	public LocalTilingSink(final CompgraphEdge<RandomAccessibleInterval<IO>> in) {
		super(in);
	}

	@Override
	protected TilingRequestable<IO> getInternal(final TilingDataHandle<IO> inData) {
		return inData.inner();
	}
}
