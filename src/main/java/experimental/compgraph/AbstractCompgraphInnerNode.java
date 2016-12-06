
package experimental.compgraph;

public abstract class AbstractCompgraphInnerNode<IN extends DataHandle<I, ?>, I, O, OUT extends DataHandle<O, ?>> extends
	AbstractCompgraphNode<O, OUT> implements CompgraphInnerNode<IN, I, O, OUT>
{

	private final CompgraphEdge<I> in;

	public AbstractCompgraphInnerNode(final CompgraphEdge<I> in, final CompgraphNodeFactory factory) {
		super(factory);
		this.in = in;
	}

	// -- CompgraphInnerNode --

	@Override
	public CompgraphEdge<I> in() {
		return in;
	}
}
