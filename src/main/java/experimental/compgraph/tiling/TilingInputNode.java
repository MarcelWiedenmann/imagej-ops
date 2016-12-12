
package experimental.compgraph.tiling;

import experimental.compgraph.CompgraphInputNode;
import experimental.compgraph.DataHandle;

public interface TilingInputNode<I, IN extends DataHandle<Tiling<I>, ?>> extends CompgraphInputNode<Tiling<I>, IN> {
	// NB: Marker interface.
}
