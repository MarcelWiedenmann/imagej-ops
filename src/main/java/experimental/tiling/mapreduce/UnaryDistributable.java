
package experimental.tiling.mapreduce;

import net.imagej.ops.special.function.UnaryFunctionOp;

import experimental.tiling.DistributedCollection;

public interface UnaryDistributable<I, O> extends UnaryFunctionOp<I, O> {

	DistributedCollection<?, O> getDistributionPlan(DistributedCollection<?, I> c);
}
