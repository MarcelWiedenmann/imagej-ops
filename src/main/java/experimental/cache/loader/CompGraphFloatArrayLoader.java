package experimental.cache.loader;

import java.util.Map;

import net.imglib2.img.basictypeaccess.volatiles.array.VolatileFloatArray;
import net.imglib2.type.NativeType;
import net.imglib2.util.IntervalIndexer;

import bdv.img.cache.CacheArrayLoader;
import experimental.compgraph.tiling.LazyTile;
import experimental.compgraph.tiling.request.TilingBulkRequestable;

public class CompGraphFloatArrayLoader<T extends NativeType<T>> implements CacheArrayLoader<VolatileFloatArray> {

	private VolatileFloatArray theEmptyArray;
	private TilingBulkRequestable<T, T> req;
	private Map<Long, LazyTile<T>> res;
	private boolean staged = false;

	public CompGraphFloatArrayLoader(final TilingBulkRequestable<T, T> req) {
		this.req = req;
		theEmptyArray = new VolatileFloatArray(64 * 64 * 64, false);
	}

	@Override
	public int getBytesPerElement() {
		return 4;
	}

	public void stage(long idx) {
		synchronized (res) {
			req.request(idx);
			staged = true;
		}
	}

	public void done() {
		synchronized (res) {
			if (staged != false) {
				req.flush();
				staged = false;
			}
		}
	}

	@Override
	public VolatileFloatArray loadArray(int timepoint, int setup, int level, int[] dimensions, long[] min)
			throws InterruptedException {
		// get tileIdx
		res.get(IntervalIndexer.positionToIndex(min, dimensions));
		return null;
	}

	@Override
	public VolatileFloatArray emptyArray(int[] dimensions) {
		int numEntities = 1;
		for (int i = 0; i < dimensions.length; ++i)
			numEntities *= dimensions[i];
		if (theEmptyArray.getCurrentStorageArray().length < numEntities)
			theEmptyArray = new VolatileFloatArray(numEntities, false);
		return theEmptyArray;
	}

}
