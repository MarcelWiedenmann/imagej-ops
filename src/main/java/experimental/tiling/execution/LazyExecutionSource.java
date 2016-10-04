
package experimental.tiling.execution;

public class LazyExecutionSource<I> implements LazyExecutionNode<I, I> {

	private final I source;

	public LazyExecutionSource(final I source) {
		this.source = source;
	}

	private LazyExecutionSource(final LazyExecutionSource<I> node) {
		this.source = node.source;
	}

	// -- LazyExecutionNode --

	@Override
	public LazyExecutionNode<?, I> getParent() {
		return null;
	}

	@Override
	public void setParent(final LazyExecutionNode<?, I> parent) {
		throw new UnsupportedOperationException("Source nodes do not have parents.");
	}

	@Override
	public LazyExecutionSource<I> getRoot() {
		return this;
	}

	@Override
	public I get() {
		return source;
	}

	@Override
	public LazyExecutionSource<I> copy() {
		return new LazyExecutionSource<>(this);
	}
}
