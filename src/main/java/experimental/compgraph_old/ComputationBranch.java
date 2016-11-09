
package experimental.compgraph_old;

import java.util.Collections;
import java.util.List;

import net.imagej.ops.special.function.UnaryFunctionOp;

import experimental.compgraph.ComputationGraphNode;

public interface ComputationBranch<I, O> extends UnaryComputationGraphInputNode<I, O>, ComputationGraph<I, O> {

	int getLength();

	UnaryComputationGraphInputNode<I, ?> getStartNode();

	UnaryComputationGraphNode<?, O> getEndNode();

	<II> ComputationBranch<II, O> prepend(final UnaryFunctionOp<II, I> func);

	<OO> ComputationBranch<I, OO> append(final UnaryFunctionOp<O, OO> func);

	@Override
	default List<ComputationGraphInputNode<?>> getStartNodes() {
		return Collections.singletonList(getStartNode());
	}

	@Override
	default List<ComputationGraphNode<?>> getEndNodes() {
		return Collections.singletonList(getEndNode());
	}

	@Override
	ComputationBranch<I, O> copy();
}
