
package experimental.compgraph.tiling;

import java.util.function.Function;

import experimental.compgraph.AbstractCompgraphUnaryNode;
import experimental.compgraph.CompgraphSingleEdge;
import experimental.compgraph.node.Map;
import experimental.compgraph.request.Requestable;
import experimental.compgraph.request.Requests;

public class LocalMap<I, O> extends AbstractCompgraphUnaryNode<I, Requestable<Requests, I>, O, Requestable<Requests, O>>
	implements Map<I, Requestable<Requests, I>, O, Requestable<Requests, O>>
{

	private final Function<? super I, O> f;

	public LocalMap(final CompgraphSingleEdge<I> in, final Function<? super I, O> f) {
		super(in);
		this.f = f;
	}

	// -- AbstractCompgraphUnaryNode --

	@Override
	protected Requestable<Requests, O> applyInternal(final Requestable<Requests, I> inData) {

		return new Requestable<Requests, O>() {

			@Override
			public O request(final Requests r) {
				final I i = inData.request(r);
				return f.apply(i);
			}

			@Override
			public Requestable<Requests, O> inner() {
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
