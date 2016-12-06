
package experimental.compgraph;

public abstract class AbstractCompgraphNode<O, OUT extends DataHandle<O, ?>> implements CompgraphNode<O, OUT> {

	// TODO: This could be a SciJava Parameter.
	private final CompgraphNodeFactory factory;

	private CompgraphEdge<O> out;

	public AbstractCompgraphNode(final CompgraphNodeFactory factory) {
		this.factory = factory;
	}

	// -- CompgraphNode --

	@Override
	public CompgraphNodeFactory factory() {
		return factory;
	}

	@Override
	public CompgraphEdge<O> out() {
		return out;
	}

	@Override
	public void setOutEdge(final CompgraphEdge<O> out) {
		this.out = out;
	}
}
