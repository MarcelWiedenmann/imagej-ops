
package experimental.compgraph;

import net.imagej.ops.special.function.UnaryFunctionOp;

public interface ComputationBranch<I, O> extends ComputationBranchInputNode<I, O>, ComputationGraph {

	ComputationBranchInputNode<I, ?> getStartNode();

	UnaryComputationGraphNode<?, O> getEndNode();

	<I2> ComputationBranch<I2, O> concat(final UnaryFunctionOp<I2, I> func);

	<O2> ComputationBranch<I, O2> preConcat(final UnaryFunctionOp<O, O2> func);
}
