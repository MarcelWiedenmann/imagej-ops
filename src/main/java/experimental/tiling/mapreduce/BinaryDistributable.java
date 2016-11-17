
package experimental.tiling.mapreduce;

import net.imagej.ops.special.function.BinaryFunctionOp;
import net.imglib2.util.Pair;

import experimental.tiling.DistributedList;

public interface BinaryDistributable<I1, I2, O> extends BinaryFunctionOp<I1, I2, O> {

	DistributedList<? extends Pair<?, ?>, O> getDistributionPlan(DistributedList<?, I1> c1,
		DistributedList<?, I2> c2);
}
