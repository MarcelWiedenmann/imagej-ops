
package experimental.compgraph.abstracts;

import net.imagej.ops.special.function.UnaryFunctionOp;

import experimental.compgraph.interfaces.ComputationGraphNode;
import experimental.compgraph.interfaces.UnaryComputationGraphStageNode;

public abstract class AbstractUnaryComputationGraphStageNode<I, O, P extends ComputationGraphNode<I>> extends
	AbstractUnaryComputationGraphNode<I, O, P> implements UnaryComputationGraphStageNode<I, O, P>
{

	private P parent;

	public AbstractUnaryComputationGraphStageNode(final P parent, final UnaryFunctionOp<I, O> func) {
		super(func);
		this.parent = parent;
	}

	@Override
	public P getParent() {
		return parent;
	}

	@Override
	public void setParent(final P parent) {
		this.parent = parent;
	}

	@Override
	public O out() {
		return getOp().compute1(parent.out());
	}
}
