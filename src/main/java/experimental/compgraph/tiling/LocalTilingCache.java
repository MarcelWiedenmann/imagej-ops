
package experimental.compgraph.tiling;

import java.util.List;

import net.imglib2.RandomAccessibleInterval;

import experimental.compgraph.AbstractCompgraphUnaryNode;
import experimental.compgraph.CompgraphSingleEdge;
import experimental.compgraph.request.TilesRequest;
import experimental.compgraph.request.TilingRequestable;

public class LocalTilingCache<IO> extends
	AbstractCompgraphUnaryNode<RandomAccessibleInterval<IO>, TilingDataHandle<IO>, RandomAccessibleInterval<IO>, TilingDataHandle<IO>>
	implements TilingUnaryNode<IO, IO>
{

	public LocalTilingCache(final CompgraphSingleEdge<RandomAccessibleInterval<IO>> in) {
		super(in);
	}

	@Override
	protected TilingDataHandle<IO> applyInternal(final TilingDataHandle<IO> inHandle) {
		return new TilingDataHandle<IO>(new TilingRequestable<IO>() {

			@Override
			public List<RandomAccessibleInterval<IO>> request(final TilesRequest requests) {

				// TODO: Christian :D

				return inHandle.inner().request(requests);
			}
		});
	}
}
