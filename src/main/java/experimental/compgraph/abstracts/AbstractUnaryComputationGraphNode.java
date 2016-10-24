
package experimental.compgraph.abstracts;

import java.util.ArrayList;
import java.util.List;

import net.imagej.ops.special.function.UnaryFunctionOp;

import experimental.compgraph.interfaces.ComputationGraphNode;
import experimental.compgraph.interfaces.ComputationGraphStageNode;
import experimental.compgraph.interfaces.UnaryComputationGraphNode;

public abstract class AbstractUnaryComputationGraphNode<I, O> implements UnaryComputationGraphNode<I, O> {

	private final UnaryFunctionOp<I, O> func;
	private final ArrayList<ComputationGraphNode<?>> children;

	public AbstractUnaryComputationGraphNode(final UnaryFunctionOp<I, O> func) {
		this.func = func;
		// NB: Having more than one child will rarely occur.
		this.children = new ArrayList<>(1);
	}

	@Override
	public UnaryFunctionOp<I, O> getOp() {
		return func;
	}

	@Override
	public List<ComputationGraphStageNode<?>> getChildren() {
		return (List<ComputationGraphStageNode<?>>) children.clone();
	}

	@Override
	public boolean hasChild(final ComputationGraphStageNode<?> child) {
		return children.contains(child);
	}

	@Override
	public ComputationGraphStageNode<?> getChild(final int index) {
		return (ComputationGraphStageNode<?>) children.get(0);
	}

	@Override
	public boolean addChild(final ComputationGraphStageNode<?> child) {
		if (!hasChild(child)) {
			children.add(child);
			return true;
		}
		return false;
	}

	@Override
	public boolean removeChild(final ComputationGraphStageNode<?> child) {
		return children.remove(child);
	}
}
