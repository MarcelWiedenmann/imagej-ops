
package experimental.tiling.execution;

// NB: We can insert the entire execution into other execution branches as it implements LazyExecutionNode.
// This might be useful for merging branches or building sub-branches/trees.
// (Just make sure to keep graphs acyclic.)
// However, parent-dependent construction is not yet implemented.
public class LazyExecution<I, O> implements LazyExecutionNode<I, O> {

	private final LazyExecutionBranch<I, O> branch;

	@SuppressWarnings("unchecked")
	public LazyExecution(final I input, final LazyExecutionBranch<I, O> branch) {
		this.branch = branch.appendRoot(input);
	}

	private LazyExecution(final LazyExecution<I, O> node) {
		this.branch = node.branch.copy();
	}

	// -- --

	@Override
	public LazyExecutionNode<?, I> getParent() {
		// NB: We consider the entire execution (i.e. the wrapped branch) as a single node.
		return null;
	}

	@Override
	public LazyExecutionNode<?, ?> getRoot() {
		// NB: We consider the entire execution (i.e. the wrapped branch) as a single node.
		return this;
	}

	@Override
	public O get() {
		return branch.getLeaf().get();
	}

	@Override
	public LazyExecution<I, O> copy() {
		return new LazyExecution<I, O>(this);
	}
}
