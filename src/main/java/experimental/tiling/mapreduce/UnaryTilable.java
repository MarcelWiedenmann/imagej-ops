
package experimental.tiling.mapreduce;

import net.imagej.ops.special.function.UnaryFunctionOp;

public interface UnaryTilable<I, O> extends UnaryFunctionOp<I, O> {

	UnaryTilingNode<O> getTilingPlan(UnaryTilingNode<I> t);
}
