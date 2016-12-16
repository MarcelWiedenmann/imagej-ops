
package experimental.compgraph.tiling;

import net.imglib2.RandomAccessibleInterval;

import experimental.compgraph.DataHandle;
import experimental.compgraph.tiling.request.TilingRequestable;

public class TilingDataHandle<IO> implements DataHandle<RandomAccessibleInterval<IO>, TilingRequestable<IO>> {

	private final TilingRequestable<IO> inner;

	public TilingDataHandle(final TilingRequestable<IO> inner) {
		this.inner = inner;
	}

	// -- DataHandle --

	@Override
	public TilingRequestable<IO> inner() {
		return inner;
	}
}
