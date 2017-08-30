
package experimental.compgraph.tiling;

import experimental.compgraph.DataHandle;
import experimental.compgraph.channel.collection.img.OpsTile;
import experimental.compgraph.tiling.request.TilingRequestable;

public class TilingDataHandle<IO> implements DataHandle<OpsTile<IO>, TilingRequestable<IO>> {

	private final TilingRequestable<IO> inner;
	private long[] gridDims;
	private int[] tileDims;

	public TilingDataHandle(final TilingRequestable<IO> inner, final long[] gridDims, final int[] tileDims) {
		this.inner = inner;
		this.gridDims = gridDims;
		this.tileDims = tileDims;
	}

	public long[] getGridDims() {
		return gridDims;
	}

	public int[] getTileDims() {
		return tileDims;
	}

	// -- DataHandle --

	@Override
	public TilingRequestable<IO> inner() {
		return inner;
	}
}
