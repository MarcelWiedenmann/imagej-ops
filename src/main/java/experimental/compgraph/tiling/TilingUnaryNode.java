
package experimental.compgraph.tiling;

import net.imglib2.RandomAccessibleInterval;

import experimental.compgraph.CompgraphUnaryNode;

public interface TilingUnaryNode<I, O> extends TilingInnerNode<I, O>,
	CompgraphUnaryNode<RandomAccessibleInterval<I>, TilingDataHandle<I>, RandomAccessibleInterval<O>, TilingDataHandle<O>>
{

	// NB: Marker interface.
}
