
package experimental.compgraph;

import org.scijava.Context;

public abstract class AbstractCompgraphInnerNode<I, IN extends DataHandle<I, ?>, O, OUT extends DataHandle<O, ?>>
	extends AbstractCompgraphOutputNode<O, OUT> implements CompgraphInnerNode<I, IN, O, OUT>
{

	private final CompgraphEdge<I> in;

	public AbstractCompgraphInnerNode(final CompgraphEdge<I> in) {
		this.in = in;
	}

	// -- AbstractContextual --

	@Override
	public void setContext(final Context context) {
		for (final CompgraphOutputNode<?, ?> source : in.parents()) {
			source.setContext(context);
		}
		super.setContext(context);
	}

	// -- CompgraphInnerNode --

	@Override
	public CompgraphEdge<I> in() {
		return in;
	}
}
