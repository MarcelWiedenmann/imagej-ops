
package experimental.compgraph.abstracts;

import java.util.ArrayList;
import java.util.List;

import net.imagej.ops.special.function.UnaryFunctionOp;

import experimental.compgraph.interfaces.ComputationGraphNode;
import experimental.compgraph.interfaces.UnaryComputationGraphNode;

public abstract class AbstractUnaryComputationGraphNode<I, O> implements UnaryComputationGraphNode<I, O> {

	private final UnaryFunctionOp<I, O> func;
	private final ArrayList<ComputationGraphNode<?>> children;

	public AbstractUnaryComputationGraphNode(final UnaryFunctionOp<I, O> func) {
		this.func = func;
		// TODO: Having more than one child will rarely occur.
		// However, this is just a good guess and may be changed.
		this.children = new ArrayList<>(1);
	}

	@Override
	public UnaryFunctionOp<I, O> getOp() {
		return func;
	}

	@Override
	public List<ComputationGraphNode<?>> getChildren() {
		return (List<ComputationGraphNode<?>>) children.clone();
	}

	// TODO/FIXME: parent registration!

	@Override
	public void addChild(final ComputationGraphNode<?> child) {
		children.add(child);
	}

	@Override
	public boolean removeChild(final ComputationGraphNode<?> child) {
		return children.remove(child);
	}
}
