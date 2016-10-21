
package experimental.compgraph.interfaces;

import net.imagej.ops.special.function.UnaryFunctionOp;

public interface ComputationBranch<I, O> extends UnaryComputationGraphInputNode<I, O>, ComputationGraph {

	UnaryComputationGraphInputNode<I, ?> getStartNode();

	UnaryComputationGraphStageNode<?, O> getEndNode();

	<II> ComputationBranch<II, O> concat(final UnaryFunctionOp<II, I> func);

	<OO> ComputationBranch<I, OO> preConcat(final UnaryFunctionOp<O, OO> func);

	@Override
	ComputationBranch<I, O> copy();
}
