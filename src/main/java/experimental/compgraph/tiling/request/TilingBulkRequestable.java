
package experimental.compgraph.tiling.request;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.imglib2.Interval;
import net.imglib2.util.IntervalIndexer;

import experimental.compgraph.tiling.DefaultTile;
import experimental.compgraph.tiling.LazyTile;
import experimental.compgraph.tiling.Tile;

public class TilingBulkRequestable<I, O> {

	private final ConcurrentHashMap<Long, Tile> queue = new ConcurrentHashMap<>();
	private final TilingRequestable<I> source;

	public TilingBulkRequestable(final TilingRequestable<I> source) {
		this.source = source;
	}

	public void request(final Interval i) {

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

		final long i = IntervalIndexer.positionToIndex(position, gridDims);
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

	public void request(Tile next) {
		// TODO Auto-generated method stub
		
	}
}
