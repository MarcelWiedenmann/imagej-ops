
package experimental.compgraph;

import net.imagej.ops.special.function.UnaryFunctionOp;

public class DefaultUnaryComputationGraphStageNode<I, O> extends AbstractUnaryComputationGraphStageNode<I, O> {

	public DefaultUnaryComputationGraphStageNode(final ComputationGraphNode<I> parent, final UnaryFunctionOp<I, O> func) {
		super(parent, func);
	}

	@Override
	public DefaultUnaryComputationGraphStageNode<I, O> copy() {
		return new DefaultUnaryComputationGraphStageNode<>(getParent().copy(), getOp().getIndependentInstance());
	}
}
