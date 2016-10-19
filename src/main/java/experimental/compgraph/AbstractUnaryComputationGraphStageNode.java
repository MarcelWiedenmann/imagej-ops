
package experimental.compgraph;

import net.imagej.ops.special.function.UnaryFunctionOp;

public abstract class AbstractUnaryComputationGraphStageNode<I, O> extends AbstractUnaryComputationGraphNode<I, O>
	implements UnaryComputationGraphStageNode<I, O>
{
	// TODO: move basic stuff to super class

	private ComputationGraphNode<I> parent;
	private final UnaryFunctionOp<I, O> func;

	public AbstractUnaryComputationGraphStageNode(final ComputationGraphNode<I> parent,
		final UnaryFunctionOp<I, O> func)
	{
		this.parent = parent;
		this.func = func;
	}

	@Override
	public ComputationGraphNode<I> getParent() {
		return parent;
	}

	@Override
	public void setParent(final ComputationGraphNode<I> parent) {
		this.parent = parent;
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
