
package experimental.compgraph.implementations;

import net.imagej.ops.special.function.UnaryFunctionOp;

import experimental.compgraph.abstracts.AbstractUnaryComputationGraphInputNode;

public class DefaultComputationBranchInputNode<I, O> extends AbstractUnaryComputationGraphInputNode<I, O> {

	public DefaultComputationBranchInputNode(final UnaryFunctionOp<I, O> func) {
		super(func);
	}

	public DefaultComputationBranchInputNode(final UnaryFunctionOp<I, O> func, final I input) {
		super(func, input);
	}

	@Override
	public DefaultComputationBranchInputNode<I, O> copyUpstream() {
		return new DefaultComputationBranchInputNode<>(getOp().getIndependentInstance(), in());
	}
}
