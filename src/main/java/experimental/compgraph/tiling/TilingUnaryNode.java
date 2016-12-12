
package experimental.compgraph.tiling;

import experimental.compgraph.CompgraphUnaryNode;
import experimental.compgraph.DataHandle;

public interface TilingUnaryNode<I, IN extends DataHandle<Tiling<I>, ?>, O, OUT extends DataHandle<Tiling<O>, ?>>
	extends TilingInnerNode<I, IN, O, OUT>, CompgraphUnaryNode<Tiling<I>, IN, Tiling<O>, OUT>
{
	// NB: Marker interface.
}
