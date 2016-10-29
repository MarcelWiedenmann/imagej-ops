
package experimental.compgraph.abstracts;

import net.imagej.ops.special.function.UnaryFunctionOp;

import experimental.compgraph.interfaces.ComputationGraphNode;
import experimental.compgraph.interfaces.UnaryComputationGraphStageNode;

public abstract class AbstractUnaryComputationGraphStageNode<I, O> extends AbstractUnaryComputationGraphNode<I, O>
	implements UnaryComputationGraphStageNode<I, O>
{

	private ComputationGraphNode<I> parent;

	public AbstractUnaryComputationGraphStageNode(final ComputationGraphNode<I> parent,
		final UnaryFunctionOp<I, O> func)
	{
		super(func);
		this.parent = parent;
	}

	@Override
	public ComputationGraphNode<I> getParent() {
		return parent;
	}

	@Override
	public void setParent(final ComputationGraphNode<I> parent) {
		this.parent = parent;
	}
}
