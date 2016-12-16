
package experimental.compgraph.tiling.node;

import net.imglib2.RandomAccessibleInterval;

import experimental.compgraph.CompgraphUnaryNode;
import experimental.compgraph.tiling.TilingDataHandle;

public interface TilingUnaryNode<I, O> extends TilingInnerNode<I, O>,
	CompgraphUnaryNode<RandomAccessibleInterval<I>, TilingDataHandle<I>, RandomAccessibleInterval<O>, TilingDataHandle<O>>
{

	// NB: Marker interface.
}
