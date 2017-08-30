
package experimental.compgraph.node;

import java.util.function.Function;

import experimental.compgraph.AbstractCompgraphUnaryNode;
import experimental.compgraph.CompgraphSingleEdge;
import experimental.compgraph.LocalDataHandle;

public class LocalFlatMap<I, O> extends AbstractCompgraphUnaryNode<I, LocalDataHandle<I>, O, LocalDataHandle<O>> implements
	Map<I, LocalDataHandle<I>, O, LocalDataHandle<O>>
{

	private final Function<? super I, O> f;

	public LocalFlatMap(final CompgraphSingleEdge<I> in, final Function<? super I, O> f) {
		super(in);
		this.f = f;
	}

	// -- AbstractCompgraphUnaryNode --

	@Override
	protected LocalDataHandle<O> applyInternal(final LocalDataHandle<I> inData) {
		return new LocalDataHandle<>(inData.inner().map(f));
	}

	// -- Map --

	@Override
	public Function<? super I, O> func() {
		return f;
	}
}
