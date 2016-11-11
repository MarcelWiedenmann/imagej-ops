
package experimental.tiling.mapreduce;

import net.imagej.ops.Ops.Join;
import net.imagej.ops.special.function.BinaryFunctionOp;

import experimental.tiling.DistributedCollection;

public interface BinaryDistributable<I1, I2, O> extends BinaryFunctionOp<I1, I2, O> {

	Join<DistributedCollection<?, I1>, DistributedCollection<?, I2>, O> getDistributionPlan(
		DistributedCollection<?, I1> c1, DistributedCollection<?, I2> c2);
}
