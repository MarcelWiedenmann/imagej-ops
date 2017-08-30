
package experimental.compgraph;

import org.scijava.Context;

public interface CompgraphInputNode<I, IN extends DataHandle<I, ?>> extends CompgraphNode {

	CompgraphEdge<I> in();

	// -- CompgraphNode --

	@Override
	default void setContext(final Context context) {
		CompgraphNode.super.setContext(context);
		for (final CompgraphNode parent : in().parents()) {
			parent.setContext(context);
		}
	}
}
