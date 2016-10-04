
package experimental.tiling.execution;

import net.imagej.ops.special.function.UnaryFunctionOp;

// TODO: We will introduce binary stages to allow fork-join workflows (i.e. junctions of LazyExecutionBranch objects).
public class LazyExecutionStage<I, O> implements LazyExecutionNode<I, O> {

	private LazyExecutionNode<?, I> parent;
	private final UnaryFunctionOp<I, O> op;

	public LazyExecutionStage(final LazyExecutionNode<?, I> parent, final UnaryFunctionOp<I, O> op) {
		this.parent = parent;
		this.op = op;
	}

	private LazyExecutionStage(final LazyExecutionStage<I, O> node) {
		parent = node.getParent().copy();
		op = node.op;
	}

	// -- LazyExecutionNode --

	@Override
	public LazyExecutionNode<?, I> getParent() {
		return parent;
	}

	@Override
	public void setParent(final LazyExecutionNode<?, I> parent) {
		this.parent = parent;
	}

	@Override
	public LazyExecutionNode<?, ?> getRoot() {
		return parent != null ? parent.getRoot() : this;
	}

	@Override
	public O get() {
		return op.compute1(parent.get());
	}

	@Override
	public LazyExecutionStage<I, O> copy() {
		return new LazyExecutionStage<>(this);
	}
}
