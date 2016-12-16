
package experimental.compgraph.tiling.request;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.imglib2.Interval;

import experimental.compgraph.request.DefaultTilesRequest;
import experimental.compgraph.tiling.DefaultTile;
import experimental.compgraph.tiling.LazyTile;
import experimental.compgraph.tiling.Tile;

public class TilingBulkRequestable<I, O> {

	private final ConcurrentHashMap<Long, Tile> queue = new ConcurrentHashMap<>();
	private final TilingRequestable<I> source;
	private final long[] gridDims;
	private final int[] tileDims;
	// NB: Keep those internal! They must not be modified.
	private final long[] fullTileMin;
	private final long[] fullTileMax;

	public TilingBulkRequestable(final TilingRequestable<I> source, final long[] gridDims, final int[] tileDims) {
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

	public int[] getTileDims() {
		return tileDims;
	}

	public void request(final long flatIndex) {
		requestInternal(flatIndex, fullTileMin, fullTileMax);
	}

	public void request(final Tile tile) {
		requestInternal(tile.flatIndex(), tile.min(), tile.max());
	}

	public void request(final Interval interval) {
		final int n = interval.numDimensions();
		final long[] minIndex = new long[n];
		final long[] maxIndex = new long[n];
		final long[] minOffset = new long[n];
		final long[] maxOffset = new long[n];
		long minFlatIndex = 0;
		long maxFlatIndex = 0;
		for (int d = n - 1; d >= 0; --d) {
			minIndex[d] = interval.min(d) / tileDims[d];
			maxIndex[d] = interval.max(d) / tileDims[d];
			minOffset[d] = interval.min(d) % tileDims[d];
			maxOffset[d] = interval.max(d) % tileDims[d] != 0 ? interval.max(d) % tileDims[d] : fullTileMax[d];
			minFlatIndex = minFlatIndex * gridDims[d] + minIndex[d];
			maxFlatIndex = maxFlatIndex * gridDims[d] + maxIndex[d];
		}
		// Interval spans a single tile.
		if (minFlatIndex == maxFlatIndex) {
			final long[] min = new long[n];
			final long[] max = new long[n];
			interval.min(min);
			interval.max(max);
			requestInternal(minFlatIndex, min, max);
			return;
		}
		// Interval spans multiple tiles: traverse all covered tiles.
		requestAll(minIndex, maxIndex, minOffset, maxOffset, gridDims, fullTileMax, 0, new long[n]);
	}

	public synchronized Map<Long, LazyTile<I>> flush() {
		final Map<Long, LazyTile<I>> res = new ConcurrentHashMap<>();
		final Enumeration<Long> keys = queue.keys();
		while (keys.hasMoreElements()) {
			final Iterator<LazyTile<I>> requesteds = source.request(new DefaultTilesRequest(new Iterator<Tile>() {

				@Override
				public boolean hasNext() {
					return keys.hasMoreElements();
				}

				@Override
				public Tile next() {
					return queue.get(keys.nextElement());
				}
			}));

			while (requesteds.hasNext()) {
				final LazyTile<I> next = requesteds.next();
				if (res.containsKey(next.flatIndex())) {
					res.put(next.flatIndex(), next);
				}
			}
		}
		queue.clear();
		return res;
	}

	// -- Private Methods --

	// NB: min/max are grid indices
	private void requestAll(final long[] min, final long[] max, final long[] minOffset, final long[] maxOffset,
		final long[] gridDims, final long[] fullTileMax, final int d, final long[] p)
	{
		for (long i = min[d]; i <= max[d]; i++) {
			p[d] = i;
			if (d == p.length - 1) {
				// Request single tile. TODO: note this is super inefficient
				long tileFlatIndex = 0;
				final long[] tileMin = new long[p.length];
				final long[] tileMax = new long[p.length];
				for (int deh = p.length - 1; deh >= 0; --deh) {
					tileFlatIndex = tileFlatIndex * gridDims[deh] + p[deh];
					tileMin[deh] = p[deh] == min[deh] ? minOffset[d] : 0;
					tileMax[deh] = p[deh] == max[deh] ? maxOffset[d] : fullTileMax[d];
				}
			}
			else {
				requestAll(min, max, minOffset, maxOffset, gridDims, fullTileMax, d + 1, p);
			}
		}
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
}
