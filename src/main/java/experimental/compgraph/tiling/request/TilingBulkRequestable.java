
package experimental.compgraph.tiling.request;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.imglib2.Interval;

import experimental.compgraph.tiling.DefaultTile;
import experimental.compgraph.tiling.LazyTile;
import experimental.compgraph.tiling.Tile;

public class TilingBulkRequestable<I, O> {

	private final ConcurrentHashMap<Long, Tile> queue = new ConcurrentHashMap<>();
	private final TilingRequestable<I> source;
	private final long[] gridDims;
	private final long[] tileDims;
	// NB: Keep those internal! They must not be modified.
	private final long[] fullTileMin;
	private final long[] fullTileMax;

	public TilingBulkRequestable(final TilingRequestable<I> source, final long[] gridDims, final long[] tileDims) {
		this.source = source;
		this.gridDims = gridDims;
		this.tileDims = tileDims;
		this.fullTileMin = new long[tileDims.length];
		this.fullTileMax = new long[tileDims.length];
		for (int d = 0; d < fullTileMax.length; d++) {
			// Implicitly: fullTileMin[d] = 0;
			fullTileMax[d] = tileDims[d] - 1;
		}
	}

	public long[] getGridDims() {
		return gridDims;
	}

	public long[] getTileDims() {
		return tileDims;
	}

	public void request(final Interval interval) {
		final int n = interval.numDimensions();
		final long[] min = new long[n];
		final long[] max = new long[n];
		interval.min(min);
		interval.max(max);

		final long[] minIndex = new long[n];
		final long[] maxIndex = new long[n];

		for (int d = n - 1; d >= 0; --d) {
			minIndex[d] = normalizedPosition / blockSize[d];
			maxIndex[d] = offset[d] = normalizedPosition % blockSize[d];
			flatIndex = flatIndex * source.dimension(d) + tempIndex[d];
		}
	}

	public void request(final long flatIndex) {
		requestInternal(flatIndex, fullTileMin, fullTileMax);
	}

	public void request(final Tile tile) {
		requestInternal(tile.flatIndex(), tile.min(), tile.max());
	}

	private void requestInternal(final long index, final long[] min, final long[] max) {
		// (1) find tile or stage tiling request
		Tile t;
		if ((t = queue.get(index)) == null) {
			t = enqueue(index, min, max);
		}
		else {
			if (!t.isComplete()) {
				// TODO check overlap and enqueue if needed
			}
		}
	}

	private Tile enqueue(final long index, final long[] min, final long[] max) {
		final long[] globalMin = new long[min.length];
		final long[] globalMax = new long[max.length];
		for (int d = 0; d < n; d++) {
			globalMin[d] = gridDims[d] * tileDims[d] + min[d];
			globalMax[d] = globalMin[d] + tileDims[d] - 1 + max[d];
		}
		tiles.request(new DefaultTile(globalMin, globalMax, i));
		// TODO return some representation of a tile, we don't really care
		// TODO add to list
		return null;
	}

	public synchronized Map<Long, LazyTile<I>> flush() {
		final Map<Long, LazyTile<I>> res = new ConcurrentHashMap<>();
		final Enumeration<Long> keys = queue.keys();
		while (keys.hasMoreElements()) {
			final Iterator<LazyTile<I>> request = source.request(new TilesRequest() {

				@Override
				public Iterator<Tile> key() {
					return new Iterator<Tile>() {

						@Override
						public boolean hasNext() {
							return keys.hasMoreElements();
						}

						@Override
						public Tile next() {
							return queue.get(keys.nextElement());
						}
					};
				}
			});

			while (request.hasNext()) {
				final LazyTile<I> next = request.next();
				if (res.containsKey(next.flatIndex())) {
					res.put(next.flatIndex(), next);
				}
			}
		}
		queue.clear();
		return res;
	}
}
