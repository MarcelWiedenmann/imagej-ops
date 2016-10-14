
package experimental.compgraph;

import net.imagej.ops.special.function.UnaryFunctionOp;

public class DefaultUnaryComputationGraphNode<I, O> extends AbstractUnaryComputationGraphNode<I, O> {

	public DefaultUnaryComputationGraphNode(final ComputationGraphNode<I> parent, final UnaryFunctionOp<I, O> func) {
		super(parent, func);
	}

	@Override
	public DefaultUnaryComputationGraphNode<I, O> copy() {
		return new DefaultUnaryComputationGraphNode<>(parent.copy(), func.getIndependentInstance());
	}
}
