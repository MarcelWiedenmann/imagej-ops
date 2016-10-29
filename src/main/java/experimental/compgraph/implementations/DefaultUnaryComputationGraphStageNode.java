
package experimental.compgraph.implementations;

import net.imagej.ops.special.function.UnaryFunctionOp;

import experimental.compgraph.abstracts.AbstractUnaryComputationGraphStageNode;
import experimental.compgraph.interfaces.ComputationGraphNode;

public class DefaultUnaryComputationGraphStageNode<I, O> extends AbstractUnaryComputationGraphStageNode<I, O> {

	public DefaultUnaryComputationGraphStageNode(final ComputationGraphNode<I> parent, final UnaryFunctionOp<I, O> func) {
		super(parent, func);
	}

	@Override
	public O out() {
		return getOp().compute1(getParent().out());
	}

	@Override
	public DefaultUnaryComputationGraphStageNode<I, O> copy() {
		return new DefaultUnaryComputationGraphStageNode<>(getParent().copy(), getOp());
	}
}
