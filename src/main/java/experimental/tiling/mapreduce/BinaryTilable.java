
package experimental.tiling.mapreduce;

import net.imagej.ops.special.function.BinaryFunctionOp;

public interface BinaryTilable<I1, I2, O> extends BinaryFunctionOp<I1, I2, O> {

	UnaryTilingNode<O> getTilingPlan(BinaryTilingNode<I1, I2> t);
}
