
package experimental.compgraph;

import java.util.Collection;

// TODO: introduce abstract source node
public class LocalCollectionSourceNode<O> implements CompgraphSourceNode<O, LocalDataHandle<O>> {

	private final LocalDataHandle<O> inData;
	private CompgraphEdge<O> out;

	public LocalCollectionSourceNode(final Collection<O> inData) {
		this.inData = new LocalDataHandle<>(inData.parallelStream());
	}

	@Override
	public CompgraphNodeFactory factory() {
		return new CompgraphNodeFactory();
	}

	@Override
	public CompgraphEdge<O> out() {
		return out;
	}

	@Override
	public void setOutEdge(final CompgraphEdge<O> out) {
		this.out = out;
	}

	@Override
	public LocalDataHandle<O> apply() {
		return inData;
	}
}
