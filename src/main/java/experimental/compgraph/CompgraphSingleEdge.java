
package experimental.compgraph;

public interface CompgraphSingleEdge<IO> extends CompgraphEdge<IO> {

//	FIXME: typing
	CompgraphNode<?, ? extends CompgraphNodeBody<?, ? extends CompgraphSingleEdge<IO>>, ? extends CompgraphSingleEdge<IO>>
		source();
}
