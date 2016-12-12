
package experimental.compgraph.tiling;

import experimental.compgraph.CompgraphOutputNode;
import experimental.compgraph.DataHandle;

public interface TilingOutputNode<O, OUT extends DataHandle<Tiling<O>, ?>> extends CompgraphOutputNode<Tiling<O>, OUT> {
	// NB: Marker interface.
}
