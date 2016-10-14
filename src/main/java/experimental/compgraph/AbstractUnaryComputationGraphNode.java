
package experimental.compgraph;

import net.imagej.ops.special.function.UnaryFunctionOp;

public abstract class AbstractUnaryComputationGraphNode<I, O> implements UnaryComputationGraphNode<I, O> {

	protected final ComputationGraphNode<I> parent;
	protected final UnaryFunctionOp<I, O> func;

	public AbstractUnaryComputationGraphNode(final ComputationGraphNode<I> parent, final UnaryFunctionOp<I, O> func) {
		this.parent = parent;
		this.func = func;
	}

	@Override
	public ComputationGraphNode<I> getParent() {
		return parent;
	}

	@Override
	public UnaryFunctionOp<I, O> getOp() {
		return func;
	}

	@Override
	public O out() {
		return func.compute1(parent.out());
	}
}
