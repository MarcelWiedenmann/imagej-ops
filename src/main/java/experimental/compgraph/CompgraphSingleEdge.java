
package experimental.compgraph;

public interface CompgraphSingleEdge<IO> extends CompgraphEdge<IO> {

	CompgraphNode<?, ?, ? extends CompgraphSingleEdge<IO>> source();

	Dataflow<IO, ?> dataflow();
}
