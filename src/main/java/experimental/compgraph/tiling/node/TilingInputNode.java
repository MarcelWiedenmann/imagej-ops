
package experimental.compgraph.tiling.node;

import experimental.compgraph.CompgraphInputNode;
import experimental.compgraph.channel.collection.img.OpsTile;
import experimental.compgraph.tiling.TilingDataHandle;

public interface TilingInputNode<I> extends CompgraphInputNode<OpsTile<I>, TilingDataHandle<I>> {

	// NB: Marker interface.
}
