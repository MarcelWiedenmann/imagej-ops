
package experimental.compgraph;

import net.imagej.ops.special.function.UnaryFunctionOp;

public class DefaultComputationBranchNode<I, O> extends AbstractComputationBranchNode<I, O> {

	public DefaultComputationBranchNode(final UnaryComputationGraphNode<?, I> parent, final UnaryFunctionOp<I, O> func) {
		super(parent, func);
	}

	@Override
	public DefaultComputationBranchNode<I, O> copy() {
		return new DefaultComputationBranchNode<>(parent.copy(), func.getIndependentInstance());
	}
}
