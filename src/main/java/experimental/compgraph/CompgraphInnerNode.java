
package experimental.compgraph;

public interface CompgraphInnerNode<I, IN extends DataHandle<I, ?>, O, OUT extends DataHandle<O, ?>> extends
	CompgraphInputNode<I, IN>, CompgraphOutputNode<O, OUT>
{

}
