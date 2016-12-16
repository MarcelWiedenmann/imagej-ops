
package experimental.compgraph.request;

import net.imagej.ops.special.function.UnaryFunctionOp;
import net.imglib2.Interval;

import experimental.compgraph.tiling.TilingActivator;

public interface UnaryInvertibleIntervalFunction<I, O> extends UnaryFunctionOp<I, O> {

	Interval invert(Tile t, TilingActivator activator);
}
