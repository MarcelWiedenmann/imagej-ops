
package experimental.tiling.mapreduce;

import net.imagej.ops.special.function.UnaryFunctionOp;

import experimental.compgraph.ComputationGraphNode;
import experimental.compgraph.Input;
import experimental.compgraph.UnaryStage;

public interface UnaryDistributable<I, O> extends UnaryFunctionOp<I, O> {

	ComputationGraphNode<UnaryStage<Input<?>, I>, O> getDistributionPlan(ComputationGraphNode<Input<?>, I> t);
}
