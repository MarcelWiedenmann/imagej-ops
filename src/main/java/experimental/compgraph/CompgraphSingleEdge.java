
package experimental.compgraph;

import java.util.Collections;
import java.util.Set;

public interface CompgraphSingleEdge<IO> extends CompgraphEdge<IO> {

	CompgraphOutputNode<IO, ? extends DataHandle<IO, ?>> parent();

	DataHandle<IO, ?> dataflow();

	// -- CompgraphEdge --

	@Override
	default Set<CompgraphOutputNode<?, ?>> parents() {
		return Collections.singleton(parent());
	}
}
