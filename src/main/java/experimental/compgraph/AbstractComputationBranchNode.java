
package experimental.compgraph;

import net.imagej.ops.special.function.UnaryFunctionOp;

public abstract class AbstractComputationBranchNode<I, O> implements UnaryComputationGraphNode<I, O> {

	protected final UnaryComputationGraphNode<?, I> parent;
	protected final UnaryFunctionOp<I, O> func;

	public AbstractComputationBranchNode(final UnaryComputationGraphNode<?, I> parent, final UnaryFunctionOp<I, O> func) {
		this.parent = parent;
		this.func = func;
	}

	protected AbstractComputationBranchNode(final UnaryFunctionOp<I, O> func) {
		this(null, func);
	}

	@Override
	public UnaryComputationGraphNode<?, I> getParent() {
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
