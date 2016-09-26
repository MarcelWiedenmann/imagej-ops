
package experimental.tiling;

import net.imagej.ops.cached.CachedOpEnvironment.CachedFunctionOp;

public class LazyTile<I, O> {

	private final LazyTile<?, I> parent;
	private final CachedFunctionOp<I, O> op;

	public LazyTile(final LazyTile<?, I> parent, final CachedFunctionOp<I, O> op) {
		this.parent = parent;
		this.op = op;
	}

	public O get() {
		return op.compute1(parent.get());
	}
}
