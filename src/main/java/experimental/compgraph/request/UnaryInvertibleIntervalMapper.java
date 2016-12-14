
package experimental.compgraph.request;

import net.imglib2.Interval;

import experimental.compgraph.tiling.TilingMask;

public interface UnaryInvertibleIntervalMapper {

	void invert(Interval i, TilingMask activator);
}
