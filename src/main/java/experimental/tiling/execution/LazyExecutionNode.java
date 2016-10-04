
package experimental.tiling.execution;

public interface LazyExecutionNode<I, O> {

	public LazyExecutionNode<?, I> getParent();

	// NB: This setter should not be called from clients as misuse will violate synchronization between nodes and
	// branches.
	public void setParent(LazyExecutionNode<?, I> parent);

	public LazyExecutionNode<?, ?> getRoot();

	public O get();

	public LazyExecutionNode<I, O> copy();
}
