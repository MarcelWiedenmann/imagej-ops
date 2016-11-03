
package experimental.tiling.mapreduce;

import net.imagej.ops.special.function.UnaryFunctionOp;

import experimental.compgraph.interfaces.ComputationGraphNode;
import experimental.compgraph.interfaces.ComputationGraphNode.Input;
import experimental.compgraph.interfaces.ComputationGraphNode.UnaryStage;

public interface UnaryDistributable<I, O> extends UnaryFunctionOp<I, O> {

	ComputationGraphNode<UnaryStage<Input<?>, I>, O> getDistributionPlan(ComputationGraphNode<Input<?>, I> t);
}
