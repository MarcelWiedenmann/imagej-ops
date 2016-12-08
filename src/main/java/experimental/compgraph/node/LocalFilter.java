
package experimental.compgraph.node;

import java.util.function.Predicate;

import experimental.compgraph.AbstractCompgraphUnaryNode;
import experimental.compgraph.CompgraphSingleEdge;
import experimental.compgraph.LocalDataHandle;

public class LocalFilter<I> extends AbstractCompgraphUnaryNode<I, LocalDataHandle<I>, I, LocalDataHandle<I>> implements
	Filter<I, LocalDataHandle<I>>
{

	private final Predicate<? super I> f;

	public LocalFilter(final CompgraphSingleEdge<I> in, final Predicate<? super I> f) {
		super(in);
		this.f = f;
	}

	// -- AbstractCompgraphUnaryNode --

	@Override
	protected LocalDataHandle<I> applyInternal(final LocalDataHandle<I> inData) {
		return new LocalDataHandle<>(inData.inner().filter(f));
	}

	// -- Filter --

	@Override
	public Predicate<? super I> func() {
		return f;
	}
}
