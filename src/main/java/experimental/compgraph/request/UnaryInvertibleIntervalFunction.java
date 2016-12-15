
package experimental.compgraph.request;

import net.imglib2.Interval;

import experimental.compgraph.tiling.TilingActivator;

public interface UnaryInvertibleIntervalFunction {

	Interval invert(Tile t, TilingActivator activator);
}
