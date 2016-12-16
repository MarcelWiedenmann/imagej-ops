
package experimental.compgraph.tiling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import net.imglib2.RandomAccessibleInterval;

import org.scijava.cache.CacheService;

import experimental.compgraph.AbstractCompgraphUnaryNode;
import experimental.compgraph.CompgraphSingleEdge;
import experimental.compgraph.request.Tile;
import experimental.compgraph.request.TilesRequest;
import experimental.compgraph.request.TilingRequestable;

public class LocalTilingCache<I> extends
		AbstractCompgraphUnaryNode<RandomAccessibleInterval<I>, TilingDataHandle<I>, RandomAccessibleInterval<I>, TilingDataHandle<I>>
		implements TilingUnaryNode<I, I> {

	private CacheService cache;
	private final long hashHint;

	public LocalTilingCache(final CacheService cache, final CompgraphSingleEdge<RandomAccessibleInterval<I>> in) {
		super(in);
		this.cache = cache;
		this.hashHint = hashCode() * 31;
	}

	// -- AbstractCompgraphUnaryNode --

	@Override
	protected TilingDataHandle<I> applyInternal(final TilingDataHandle<I> inHandle) {
		return new TilingDataHandle<>(new TilingRequestable<I>() {

			@Override
			public Iterator<LazyTile<I>> request(final TilesRequest request) {
				final HashMap<Long, LazyTile<I>> res = new HashMap<>();

				final ArrayList<Long> indices = new ArrayList<>();

				final TilingBulkRequestable<I, I> bulk = new TilingBulkRequestable<>(inHandle.inner());

				final Iterator<Tile> key = request.key();
				while (key.hasNext()) {
					final Tile next = key.next();
					indices.add(next.flatIndex());
					Object object = cache.get(hashHint ^ next.flatIndex());

					if (object != null) {
						@SuppressWarnings("unchecked")
						LazyTile<I> lt = (LazyTile<I>) object;
						// TODO check if big enough
						// if not, act as if object was null, else stage object
						res.put(lt.flatIndex(), lt);
					} else {
						bulk.request(next);
					}
				}

				final Map<Long, LazyTile<I>> requested = bulk.flush();

				// Cache and add to result
				for (final Entry<Long, LazyTile<I>> entry : requested.entrySet()) {
					res.put(entry.getKey(), entry.getValue());
					cache.put(hashHint ^ entry.getKey(), entry.getValue());
				}

				res.putAll(requested);

				return new Iterator<LazyTile<I>>() {

					final Iterator<Long> it = indices.iterator();

					@Override
					public boolean hasNext() {
						return it.hasNext();
					}

					@Override
					public LazyTile<I> next() {
						return res.get(it.next());
					}
				};
			}
		});
	}

}
