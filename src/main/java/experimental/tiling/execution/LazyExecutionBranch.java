
package experimental.tiling.execution;

import net.imagej.ops.cached.CachedOpEnvironment.CachedFunctionOp;

// NB: Full trees could be modeled using junctions of branches.
public class LazyExecutionBranch<I, O> {

	private final LazyExecutionStep<I, ?> root;
	private final LazyExecutionStep<?, O> leaf;

	public LazyExecutionBranch(final CachedFunctionOp<I, O> op) {
		final LazyExecutionStep<I, O> tile = new LazyExecutionStep<I, O>(null, op);
		root = tile;
		leaf = tile;
	}

	@SuppressWarnings("unchecked")
	private LazyExecutionBranch(final LazyExecutionBranch<I, O> branch) {
		leaf = branch.leaf.copy();
		root = (LazyExecutionStep<I, ?>) leaf.getRoot();
	}

	@SuppressWarnings("unchecked")
	private <IO> LazyExecutionBranch(final LazyExecutionBranch<I, IO> branch, final CachedFunctionOp<IO, O> op) {
		final LazyExecutionStep<?, IO> leafCopy = branch.leaf.copy();
		leaf = new LazyExecutionStep<>(leafCopy, op);
		root = (LazyExecutionStep<I, ?>) leafCopy.getRoot();
	}

	public LazyExecutionBranch<I, O> appendRoot(final I input) {
		// TODO NB: Caution! Current root could be an input node.
		throw new RuntimeException("Not yet implemented");
	}

	public <I2> LazyExecutionBranch<I, O> appendRoot(final CachedFunctionOp<I2, I> op) {
		// NB: Caution! Current root could be an input node.
		throw new RuntimeException("Not yet implemented");
	}

	public <O2> LazyExecutionBranch<I, O2> appendLeaf(final CachedFunctionOp<O, O2> op) {
		return new LazyExecutionBranch<>(this, op);
	}

	public <I2> LazyExecutionBranch<I, O> appendRoot(final LazyExecutionBranch<I2, I> branch) {
		// NB: Caution! Current root could be an input node.
		throw new RuntimeException("Not yet implemented");
	}

	public <O2> LazyExecutionBranch<I, O> appendLeaf(final LazyExecutionBranch<O, O2> branch) {
		throw new RuntimeException("Not yet implemented");
	}

	public LazyExecutionNode<I, ?> getRoot() {
		return root;
	}

	public LazyExecutionNode<?, O> getLeaf() {
		return leaf;
	}

	public LazyExecutionBranch<I, O> copy() {
		return new LazyExecutionBranch<>(this);
	}
}
