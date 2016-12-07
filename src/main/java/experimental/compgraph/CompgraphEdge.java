
package experimental.compgraph;

import java.util.Set;

public interface CompgraphEdge<IO> {

	// NB: Marker interface for the edges - i.e. the data flow container - of a computation graph.

	Set<CompgraphOutputNode<?, ?>> parents();

	// TODO: getName()/setName(..) -- this could be useful for named inputs in a graph/module
}
