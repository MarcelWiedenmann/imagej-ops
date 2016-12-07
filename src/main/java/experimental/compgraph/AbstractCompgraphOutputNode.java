
package experimental.compgraph;

public abstract class AbstractCompgraphOutputNode<O, OUT extends DataHandle<O, ?>> extends AbstractCompgraphNode
	implements CompgraphOutputNode<O, OUT>
{

	private CompgraphEdge<O> out;

	@Override
	public CompgraphEdge<O> out() {
		return out;
	}

	@Override
	public void setOutEdge(final CompgraphEdge<O> out) {
		this.out = out;
	}
}
