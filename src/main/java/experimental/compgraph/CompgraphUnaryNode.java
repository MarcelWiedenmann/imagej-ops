
package experimental.compgraph;

public interface CompgraphUnaryNode<IN extends DataHandle<I, ?>, I, O, OUT extends DataHandle<O, ?>> extends
	CompgraphInnerNode<IN, I, O, OUT>
{

	// -- CompgraphInnerNode --

	@Override
	CompgraphSingleEdge<I> in();
}
