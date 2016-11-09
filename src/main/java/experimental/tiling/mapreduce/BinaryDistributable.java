
package experimental.tiling.mapreduce;

import net.imagej.ops.special.function.BinaryFunctionOp;

import experimental.compgraph.BinaryStage;
import experimental.compgraph.ComputationGraphNode.ComputationGraphJoinNode;

public interface BinaryDistributable<I1, I2, O> extends BinaryFunctionOp<I1, I2, O> {

	ComputationGraphJoinNode<I1, I2, BinaryStage<?, ?, I1, I2>, O> getDistributionPlan(BinaryTilingNode<I1, I2> t);
}
