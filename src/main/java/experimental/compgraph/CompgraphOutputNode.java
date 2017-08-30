
package experimental.compgraph;

public interface CompgraphOutputNode<O, OUT extends DataHandle<O, ?>> extends CompgraphNode {

	CompgraphEdge<O> out();

	void setOutEdge(CompgraphEdge<O> out);

	OUT apply();
}
