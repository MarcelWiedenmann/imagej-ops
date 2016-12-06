
package experimental.compgraph;

import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LocalCollectionSinkNode<O> implements CompgraphSinkNode<LocalDataHandle<O>, O, O, LocalDataHandle<O>> {

	private final CompgraphSingleEdge<O> in;
	private final CompgraphNodeFactory factory;

	public LocalCollectionSinkNode(final CompgraphSingleEdge<O> in, final CompgraphNodeFactory factory) {
		this.in = in;
		this.factory = factory;
	}

	@SuppressWarnings("unchecked")
	public Collection<O> get() {
		// TODO: Eventually change collect pattern, as we want concurrency.
		return ((LocalDataHandle<O>) in.dataflow()).inner.collect(Collectors.toList());
	}

	public Collection<O> get(final Predicate<? super O> f) {
		return ((LocalDataHandle<O>) in.dataflow()).inner.filter(f).collect(Collectors.toList());
	}

	public Collection<O> getFirst(final long f) {
		return ((LocalDataHandle<O>) in.dataflow()).inner.limit(f).collect(Collectors.toList());
	}

	// -- --

	@Override
	public CompgraphSingleEdge<O> in() {
		return in;
	}

	@Override
	public CompgraphNodeFactory factory() {
		return factory;
	}

	@Override
	public CompgraphEdge<O> out() {
		// FIXME
		throw new UnsupportedOperationException("Sinks do not have outgoing edge! - Refactor");
	}

	@Override
	public void setOutEdge(final CompgraphEdge<O> out) {
		// FIXME
		throw new UnsupportedOperationException("Sinks do not have outgoing edge! - Refactor");
	}

	@Override
	public LocalDataHandle<O> apply() {
		throw new UnsupportedOperationException("Sinks do not have outgoing edge! - Refactor");
	}
}
