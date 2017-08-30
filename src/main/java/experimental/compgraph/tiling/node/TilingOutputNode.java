
package experimental.compgraph.tiling.node;

import experimental.compgraph.CompgraphOutputNode;
import experimental.compgraph.channel.collection.img.OpsTile;
import experimental.compgraph.tiling.TilingDataHandle;

public interface TilingOutputNode<O> extends CompgraphOutputNode<OpsTile<O>, TilingDataHandle<O>> {

	// NB: Marker interface.
}
