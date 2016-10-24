
package experimental.compgraph.abstracts;

import net.imagej.ops.special.function.UnaryFunctionOp;

import experimental.compgraph.interfaces.UnaryComputationGraphNode;

public abstract class AbstractUnaryComputationGraphNode<I, O> implements UnaryComputationGraphNode<I, O> {

	private final UnaryFunctionOp<I, O> func;

	public AbstractUnaryComputationGraphNode(final UnaryFunctionOp<I, O> func) {
		this.func = func;
	}

	@Override
	public UnaryFunctionOp<I, O> getOp() {
		return func;
	}
}
