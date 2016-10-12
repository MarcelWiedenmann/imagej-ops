
package experimental.compgraph;

import net.imagej.ops.special.function.UnaryFunctionOp;

public class DefaultComputationBranchInputNode<I, O> extends AbstractComputationBranchInputNode<I, O> {

	public DefaultComputationBranchInputNode(final UnaryFunctionOp<I, O> func) {
		super(func);
	}

	@Override
	public DefaultComputationBranchInputNode<I, O> copy() {
		return new DefaultComputationBranchInputNode<>(func.getIndependentInstance());
	}
}
