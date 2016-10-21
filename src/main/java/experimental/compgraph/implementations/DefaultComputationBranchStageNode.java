
package experimental.compgraph.implementations;

import net.imagej.ops.special.function.UnaryFunctionOp;

import experimental.compgraph.abstracts.AbstractUnaryComputationGraphStageNode;
import experimental.compgraph.interfaces.ComputationGraphNode;

public class DefaultComputationBranchStageNode<I, O> extends AbstractUnaryComputationGraphStageNode<I, O> {

	public DefaultComputationBranchStageNode(final ComputationGraphNode<I> parent, final UnaryFunctionOp<I, O> func) {
		super(parent, func);
	}

	@Override
	public DefaultComputationBranchStageNode<I, O> copy() {
		return new DefaultComputationBranchStageNode<>(getParent(), getOp());
	}
}
