
package experimental.compgraph.tiling.node;

import experimental.compgraph.CompgraphInnerNode;
import experimental.compgraph.channel.collection.img.OpsTile;
import experimental.compgraph.tiling.TilingDataHandle;

public interface TilingInnerNode<I, O> extends TilingInputNode<I>, TilingOutputNode<O>,
	CompgraphInnerNode<OpsTile<I>, TilingDataHandle<I>, OpsTile<O>, TilingDataHandle<O>>
{
	// NB: Marker interface.
}
