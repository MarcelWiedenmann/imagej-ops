
package experimental.compgraph.tiling;

import net.imglib2.RandomAccessibleInterval;

import experimental.compgraph.AbstractCompgraphUnaryNode;
import experimental.compgraph.CompgraphSingleEdge;
import experimental.compgraph.request.IntervalTransformRequest;
import experimental.compgraph.request.RequestableRai;

public class LocalTilingCache<IO> extends
	AbstractCompgraphUnaryNode<RandomAccessibleInterval<IO>, TilingDataHandle<IO>, RandomAccessibleInterval<IO>, TilingDataHandle<IO>>
	implements TilingUnaryNode<IO, IO>
{

	public LocalTilingCache(final CompgraphSingleEdge<RandomAccessibleInterval<IO>> in) {
		super(in);
	}

	@Override
	protected TilingDataHandle<IO> applyInternal(final TilingDataHandle<IO> inData) {
		return new TilingDataHandle<IO>() {

			@Override
			public RequestableRai<IO> inner() {
				return new RequestableRai<IO>() {

					@Override
					public RandomAccessibleInterval<IO> request(final Iterable<IntervalTransformRequest> requests) {

						// TODO: Christian :D

						return inData.inner().request(requests);
					}
				};
			}
		};
	}
}
