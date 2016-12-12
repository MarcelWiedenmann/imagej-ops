
package experimental.compgraph.tiling;

import experimental.compgraph.CompgraphInnerNode;
import experimental.compgraph.DataHandle;

public interface TilingInnerNode<I, IN extends DataHandle<Tiling<I>, ?>, O, OUT extends DataHandle<Tiling<O>, ?>>
	extends TilingInputNode<I, IN>, TilingOutputNode<O, OUT>, CompgraphInnerNode<Tiling<I>, IN, Tiling<O>, OUT>
{
	// NB: Marker interface.
}
