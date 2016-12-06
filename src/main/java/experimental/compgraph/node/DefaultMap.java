
package experimental.compgraph.node;

import java.util.function.Function;

import experimental.compgraph.AbstractCompgraphUnaryNode;
import experimental.compgraph.CompgraphNodeFactory;
import experimental.compgraph.CompgraphSingleEdge;
import experimental.compgraph.LocalDataHandle;

public class DefaultMap<IN extends LocalDataHandle<I>, I, O> extends
	AbstractCompgraphUnaryNode<IN, I, O, LocalDataHandle<O>> implements Map<IN, I, O, LocalDataHandle<O>>
{

	private final Function<? super I, O> f;

	public DefaultMap(final Function<? super I, O> f, final CompgraphSingleEdge<I> in,
		final CompgraphNodeFactory factory)
	{
		super(in, factory);
		this.f = f;
	}

	// -- AbstractCompgraphUnaryNode --

	@Override
	protected LocalDataHandle<O> applyInternal(final IN inData) {
		return new LocalDataHandle<>(inData.inner().map(f));
	}

	// -- Map --

	@Override
	public Function<? super I, O> func() {
		return f;
	}
}
