
package experimental.compgraph.implementations;

import net.imagej.ops.special.function.UnaryFunctionOp;

import experimental.compgraph.abstracts.AbstractUnaryComputationGraphStageNode;
import experimental.compgraph.interfaces.ComputationBranchNode;
import experimental.compgraph.interfaces.ComputationBranchStageNode;

public class DefaultComputationBranchStageNode<I, O> extends
	AbstractUnaryComputationGraphStageNode<I, O, ComputationBranchNode<?, I>> implements
	ComputationBranchStageNode<I, O>
{

	private ComputationBranchStageNode<O, ?> child;

	public DefaultComputationBranchStageNode(final ComputationBranchNode<?, I> parent, final UnaryFunctionOp<I, O> func) {
		super(parent, func);
	}

	@Override
	public DefaultComputationBranchStageNode<I, O> copy() {
		return new DefaultComputationBranchStageNode<>((ComputationBranchNode<?, I>) getParent().copy(), getOp()
			.getIndependentInstance());
	}

	@Override
	public ComputationBranchStageNode<O, ?> getChild() {
		return child;
	}

	@Override
	public void setChild(final ComputationBranchStageNode<O, ?> child) {
		this.child = child;
		// FIXME FIXME FIXME FIXME FIXME: establish parent-child connections where needed
	}
}
