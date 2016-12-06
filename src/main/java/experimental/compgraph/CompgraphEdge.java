
package experimental.compgraph;

public interface CompgraphEdge<IO> extends CompgraphNodeBodyOperand<IO> {

	// NB: Marker interface for the edges - i.e. the data flow container - of a computation graph.

	// TODO: getName()/setName(..) -- this could be useful for named inputs in a graph/module
}
