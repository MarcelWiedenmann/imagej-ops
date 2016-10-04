
package experimental.tiling.execution;

import net.imagej.ops.special.function.UnaryFunctionOp;

public class LazyExecutionBranch<I, O> implements LazyExecutionNode<I, O> {

	private final LazyExecutionNode<I, ?> root;
	private final LazyExecutionNode<?, O> leaf;

	public LazyExecutionBranch(final UnaryFunctionOp<I, O> op) {
		final LazyExecutionStage<I, O> tile = new LazyExecutionStage<I, O>(null, op);
		root = tile;
		leaf = tile;
	}

	private <IO> LazyExecutionBranch(final LazyExecutionNode<I, IO> node1, final LazyExecutionNode<IO, O> node2,
		final boolean copy1, final boolean copy2)
	{
		final LazyExecutionNode<I, IO> n1 = copy1 ? node1.copy() : node1;
		final LazyExecutionNode<IO, O> n2 = copy2 ? node2.copy() : node2;
		root = (LazyExecutionNode<I, ?>) n1.getRoot();
		leaf = n2;
		try {
			((LazyExecutionNode<IO, ?>) n2.getRoot()).setParent(n1);
		}
		catch (final UnsupportedOperationException ex) {
			// NB: If n2.getRoot() is a source node, assigning a new parent is not allowed. Currently, we just let the
			// execution fail in such situations. However, we could also replace the old source with the new node.
			// Manipulating our branches from the root side is cumbersome due to the single-linked nature of the graph.
			// TODO: Change to double-linked graphs?
			throw ex;
		}
	}

	private LazyExecutionBranch(final LazyExecutionBranch<I, O> branch) {
		leaf = branch.leaf.copy();
		root = (LazyExecutionNode<I, ?>) leaf.getRoot();
	}

	public LazyExecutionBranch<I, O> appendRoot(final I input) {
		return new LazyExecutionBranch<>(new LazyExecutionSource<>(input), this, false, true);
	}

	public <I2> LazyExecutionBranch<I2, O> appendRoot(final UnaryFunctionOp<I2, I> op) {
		return new LazyExecutionBranch<>(new LazyExecutionStage<I2, I>(null, op), this, false, true);
	}

	public <I2> LazyExecutionBranch<I2, O> appendRoot(final LazyExecutionBranch<I2, I> branch) {
		return new LazyExecutionBranch<I2, O>(branch, this, true, true);
	}

	public <O2> LazyExecutionBranch<I, O2> appendLeaf(final UnaryFunctionOp<O, O2> op) {
		return new LazyExecutionBranch<>(this, new LazyExecutionStage<>(null, op), true, false);
	}

	public <O2> LazyExecutionBranch<I, O2> appendLeaf(final LazyExecutionBranch<O, O2> branch) {
		return new LazyExecutionBranch<>(this, branch, true, true);
	}

	public LazyExecutionNode<?, O> getLeaf() {
		return leaf;
	}

	// -- LazyExecutionNode --

	@Override
	public LazyExecutionNode<?, I> getParent() {
		return getRoot().getParent();
	}

	@Override
	public void setParent(final LazyExecutionNode<?, I> parent) {
		getRoot().setParent(parent);
	}

	@Override
	public LazyExecutionNode<I, ?> getRoot() {
		return root;
	}

	@Override
	public O get() {
		return getLeaf().get();
	}

	@Override
	public LazyExecutionBranch<I, O> copy() {
		return new LazyExecutionBranch<>(this);
	}
}
