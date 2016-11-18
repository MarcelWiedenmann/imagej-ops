
package experimental.tiling.mapreduce;

import net.imagej.ops.special.function.UnaryFunctionOp;

import experimental.tiling.DistributedList;

public interface UnaryDistributable<I, O> extends UnaryFunctionOp<I, O> {

	DistributedList<O> getDistributionPlan(DistributedList<I> in);
}
