
package experimental.compgraph;

public interface CompgraphInnerNode<IN extends DataHandle<I, ?>, I, O, OUT extends DataHandle<O, ?>> extends
	CompgraphNode<O, OUT>
{

	CompgraphEdge<I> in();
}
