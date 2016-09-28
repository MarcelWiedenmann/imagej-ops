
package experimental.tiling.execution;

public class LazyExecutionInput<I> implements LazyExecutionNode<I, I> {

	private final I input;

	public LazyExecutionInput(final I tile) {
		this.input = tile;
	}

	private LazyExecutionInput(final LazyExecutionInput<I> node) {
		this.input = node.input;
	}

	// -- --

	@Override
	public LazyExecutionNode<?, I> getParent() {
		return null;
	}

	@Override
	public LazyExecutionInput<I> getRoot() {
		return this;
	}

	@Override
	public I get() {
		return input;
	}

	@Override
	public LazyExecutionInput<I> copy() {
		return new LazyExecutionInput<>(this);
	}
}
