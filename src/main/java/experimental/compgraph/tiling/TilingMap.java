
package experimental.compgraph.tiling;

import experimental.compgraph.DataHandle;

public interface TilingMap<I, IN extends DataHandle<Tiling<I>, ?>, O, OUT extends DataHandle<Tiling<O>, ?>> extends
	TilingUnaryNode<I, IN, O, OUT>
{
	// NB: Marker interface.
}
