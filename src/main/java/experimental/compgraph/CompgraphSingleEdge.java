
package experimental.compgraph;

import java.util.Collections;
import java.util.Set;

public interface CompgraphSingleEdge<IO> extends CompgraphEdge<IO> {

	CompgraphOutputNode<IO, ? extends DataHandle<IO, ?>> parent();

	// NB: Pull mechanism. Override if you want to introduce caching or the like.
	default DataHandle<IO, ?> dataflow() {
		return parent().apply();
	}

	// -- CompgraphEdge --

	@Override
	default Set<CompgraphOutputNode<?, ?>> parents() {
		return Collections.singleton(parent());
	}
}
