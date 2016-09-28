
package experimental.tiling.execution;

public interface LazyExecutionNode<I, O> {

	public LazyExecutionNode<?, I> getParent();

	public LazyExecutionNode<?, ?> getRoot();

	public O get();

	public LazyExecutionNode<I, O> copy();
}
