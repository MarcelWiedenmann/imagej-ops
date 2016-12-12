
package experimental.compgraph.tiling;

import java.util.function.Function;

import experimental.compgraph.AbstractCompgraphUnaryNode;
import experimental.compgraph.CompgraphSingleEdge;
import experimental.compgraph.node.Map;
import experimental.compgraph.request.ContextualRequests;
import experimental.compgraph.request.Request;
import experimental.compgraph.request.Requestable;
import experimental.compgraph.request.Requests;

public class LocalContextMap<I, O> extends
	AbstractCompgraphUnaryNode<I, Requestable<Request, I>, O, Requestable<Request, O>> implements
	Map<I, Requestable<Request, I>, O, Requestable<Request, O>>
{

	private final Function<? super I, O> f;

	private final int[] overlap = null;

	public LocalContextMap(final CompgraphSingleEdge<I> in, final Function<? super I, O> f) {
		super(in);
		this.f = f;
	}

	// -- AbstractCompgraphUnaryNode --

	@Override
	protected Requestable<Request, O> applyInternal(final Requestable<Request, I> inData) {
		return new Requestable<Request, O>() {

			@Override
			public O request(final Requests<Request> rs) {
				rs.merge(null);
				final I i = inData.request((ContextualRequests) null);

				return f.apply(i);
			}

			@Override
			public Requestable<Request, O> inner() {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}

	// -- Map --

	@Override
	public Function<? super I, O> func() {
		return f;
	}
}
