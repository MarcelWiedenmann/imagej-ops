
package experimental.compgraph.tiling.node;

import experimental.compgraph.CompgraphUnaryNode;
import experimental.compgraph.channel.collection.img.OpsTile;
import experimental.compgraph.tiling.TilingDataHandle;

public interface TilingUnaryNode<I, O> extends TilingInnerNode<I, O>,
	CompgraphUnaryNode<OpsTile<I>, TilingDataHandle<I>, OpsTile<O>, TilingDataHandle<O>>
{

	// NB: Marker interface.
}
