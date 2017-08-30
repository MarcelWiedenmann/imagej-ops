
package experimental.compgraph;

public interface CompgraphUnaryNode<I, IN extends DataHandle<I, ?>, O, OUT extends DataHandle<O, ?>> extends
	CompgraphInnerNode<I, IN, O, OUT>
{

	@Override
	CompgraphSingleEdge<I> in();
}
