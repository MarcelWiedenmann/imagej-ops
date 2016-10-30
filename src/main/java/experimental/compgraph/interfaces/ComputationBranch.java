
package experimental.compgraph.interfaces;

import java.util.Collections;
import java.util.List;

import net.imagej.ops.special.function.UnaryFunctionOp;

public interface ComputationBranch<I, O> extends UnaryComputationGraphInputNode<I, O>, ComputationGraph {

	int getLength();

	UnaryComputationGraphInputNode<I, ?> getStartNode();

	UnaryComputationGraphNode<?, O> getEndNode();

	<II> ComputationBranch<II, O> prepend(final UnaryFunctionOp<II, I> func);

	<II> ComputationBranch<II, O> prepend(final UnaryComputationGraphNode<II, I> node);

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
