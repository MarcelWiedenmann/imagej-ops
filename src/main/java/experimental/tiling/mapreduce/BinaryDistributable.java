
package experimental.tiling.mapreduce;

import net.imagej.ops.special.function.BinaryFunctionOp;

import experimental.tiling.DistributedList;

public interface BinaryDistributable<I1, I2, O> extends BinaryFunctionOp<I1, I2, O> {

	DistributedList<O> getDistributionPlan(DistributedList<I1> in1, DistributedList<I2> in2);
}
