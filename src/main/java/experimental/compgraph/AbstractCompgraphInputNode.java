
package experimental.compgraph;

public abstract class AbstractCompgraphInputNode<I, IN extends DataHandle<I, ?>> extends AbstractCompgraphNode
	implements CompgraphInputNode<I, IN>
{

	private final CompgraphEdge<I> in;

	public AbstractCompgraphInputNode(final CompgraphEdge<I> in) {
		this.in = in;
	}

	@Override
	public CompgraphEdge<I> in() {
		return in;
	}
}
