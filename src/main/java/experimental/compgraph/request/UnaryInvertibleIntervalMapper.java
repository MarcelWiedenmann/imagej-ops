
package experimental.compgraph.request;

import experimental.compgraph.tiling.TilingMask;

public interface UnaryInvertibleIntervalMapper {

	void invert(Tile t, TilingMask<?> mask);
}
