
package experimental.compgraph.tiling;

import experimental.compgraph.AbstractCompgraphUnaryNode;
import experimental.compgraph.CompgraphSingleEdge;
import experimental.compgraph.request.Requestable;
import experimental.compgraph.request.Requests;

public class LocalTilingCache<IO> extends
	AbstractCompgraphUnaryNode<Tiling<IO>, RequestableTiling<IO>, Tiling<IO>, RequestableTiling<IO>> implements
	TilingUnaryNode<IO, RequestableTiling<IO>, IO, RequestableTiling<IO>>
{

	public LocalTilingCache(final CompgraphSingleEdge<Tiling<IO>> in) {
		super(in);
	}

	@Override
	protected RequestableTiling<IO> applyInternal(final RequestableTiling<IO> inData) {
		return new RequestableTiling<IO>() {

			@Override
			public Tiling<IO> request(final Requests<TilingRequest> r) {
				// TODO: Christian :D
				return null;
			}

			@Override
			public Requestable<TilingRequest, Tiling<IO>> inner() {
				// TODO: hack, will be refactored
				return this;
			}
		};
	}
}
