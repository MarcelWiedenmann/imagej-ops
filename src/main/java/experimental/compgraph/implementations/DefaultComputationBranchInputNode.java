
package experimental.compgraph.implementations;

import net.imagej.ops.special.function.UnaryFunctionOp;

import experimental.compgraph.abstracts.AbstractUnaryComputationGraphInputNode;
import experimental.compgraph.interfaces.ComputationBranchInputNode;
import experimental.compgraph.interfaces.ComputationBranchNode;
import experimental.compgraph.interfaces.ComputationBranchStageNode;

public class DefaultComputationBranchInputNode<I, O> extends
	AbstractUnaryComputationGraphInputNode<I, O, ComputationBranchNode<?, I>> implements ComputationBranchInputNode<I, O>

{

	private ComputationBranchStageNode<O, ?> child;

	public DefaultComputationBranchInputNode(final UnaryFunctionOp<I, O> func) {
		super(func);
	}

	public DefaultComputationBranchInputNode(final UnaryFunctionOp<I, O> func, final I input) {
		super(func, input);
	}

	@Override
	public DefaultComputationBranchInputNode<I, O> copy() {
		return new DefaultComputationBranchInputNode<>(getOp().getIndependentInstance());
	}

	@Override
	public ComputationBranchStageNode<O, ?> getChild() {
		return child;
	}

	@Override
	public void setChild(final ComputationBranchStageNode<O, ?> child) {
		this.child = child;
	}
}
