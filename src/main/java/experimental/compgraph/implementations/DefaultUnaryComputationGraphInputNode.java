
package experimental.compgraph.implementations;

import net.imagej.ops.special.function.UnaryFunctionOp;

import experimental.compgraph.abstracts.AbstractUnaryComputationGraphInputNode;

public class DefaultUnaryComputationGraphInputNode<I, O> extends AbstractUnaryComputationGraphInputNode<I, O> {

	public DefaultUnaryComputationGraphInputNode(final UnaryFunctionOp<I, O> func) {
		super(func);
	}

	public DefaultUnaryComputationGraphInputNode(final UnaryFunctionOp<I, O> func, final I input) {
		super(func, input);
	}

	@Override
	public DefaultUnaryComputationGraphInputNode<I, O> copy() {
		return new DefaultUnaryComputationGraphInputNode<>(getOp().getIndependentInstance(), in());
	}
}
