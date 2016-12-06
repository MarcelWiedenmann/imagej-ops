
package experimental.compgraph;

public interface CompgraphSingleEdge<IO> extends CompgraphEdge<IO> {

	CompgraphNode<IO, ? extends DataHandle<IO, ?>> source();

	DataHandle<IO, ?> dataflow();
}
