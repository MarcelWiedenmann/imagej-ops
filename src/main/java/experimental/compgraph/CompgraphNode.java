
package experimental.compgraph;

public interface CompgraphNode<O, OUT extends DataHandle<O, ?>> {

	CompgraphNodeFactory factory();

	CompgraphEdge<O> out();

	void setOutEdge(CompgraphEdge<O> out);

	OUT apply();
}
