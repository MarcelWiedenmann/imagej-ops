
package experimental.tiling.execution;

public class LazyExecution<I, O> implements LazyExecutionNode<I, O> {

	protected final LazyExecutionBranch<I, O> branch;

	public LazyExecution(final I input, final LazyExecutionBranch<I, O> branch) {
		this.branch = branch.appendRoot(input);
	}

	private LazyExecution(final LazyExecution<I, O> node) {
		this.branch = node.branch.copy();
	}

	// -- LazyExecutionNode --

	@Override
	public LazyExecutionNode<?, I> getParent() {
		// NB: We have a fixed input, no more nodes are allowed in root-direction.
		return null;
	}

	@Override
	public void setParent(final LazyExecutionNode<?, I> parent) {
		// NB: We have a fixed input, no more nodes are allowed in root-direction.
		throw new UnsupportedOperationException("Source nodes do not have parents.");
	}

	@Override
	public LazyExecutionNode<?, ?> getRoot() {
		// NB: We consider the entire execution a single node.
		return this;
	}

	@Override
	public O get() {
		return branch.get();
	}

	@Override
	public LazyExecution<I, O> copy() {
		return new LazyExecution<I, O>(this);
	}
}
