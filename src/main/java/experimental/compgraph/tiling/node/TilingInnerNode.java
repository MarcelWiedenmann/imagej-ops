
package experimental.compgraph.tiling.node;

import net.imglib2.RandomAccessibleInterval;

import experimental.compgraph.CompgraphInnerNode;
import experimental.compgraph.tiling.TilingDataHandle;

public interface TilingInnerNode<I, O> extends TilingInputNode<I>, TilingOutputNode<O>,
	CompgraphInnerNode<RandomAccessibleInterval<I>, TilingDataHandle<I>, RandomAccessibleInterval<O>, TilingDataHandle<O>>
{
	// NB: Marker interface.
}
