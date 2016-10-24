
package experimental.compgraph.interfaces;

import net.imagej.ops.special.function.UnaryFunctionOp;

public interface ComputationBranch<I, O> extends UnaryComputationGraphInputNode<I, O>, ComputationGraph {

	UnaryComputationGraphInputNode<I, ?> getStartNode();

	UnaryComputationGraphNode<?, O> getEndNode();

	<II> ComputationBranch<II, O> prepend(final UnaryFunctionOp<II, I> func);

	<OO> ComputationBranch<I, OO> append(final UnaryFunctionOp<O, OO> func);

	@Override
	ComputationBranch<I, O> copyUpstream();
}
