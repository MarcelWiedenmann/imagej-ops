
package experimental.compgraph.tiling.node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.scijava.cache.CacheService;

import experimental.cache.loader.CacheHack;
import experimental.compgraph.AbstractCompgraphUnaryNode;
import experimental.compgraph.CompgraphSingleEdge;
import experimental.compgraph.channel.collection.img.OpsTile;
import experimental.compgraph.tiling.LazyTile;
import experimental.compgraph.tiling.Tile;
import experimental.compgraph.tiling.TilingDataHandle;
import experimental.compgraph.tiling.request.TilesRequest;
import experimental.compgraph.tiling.request.TilingBulkRequestable;
import experimental.compgraph.tiling.request.TilingRequestable;

public class LocalTilingCacheNode<I>
		extends AbstractCompgraphUnaryNode<OpsTile<I>, TilingDataHandle<I>, OpsTile<I>, TilingDataHandle<I>>
		implements TilingUnaryNode<I, I> {

	private CacheService cache = CacheHack.getCacheService();

	private final long hashHint;

	public LocalTilingCacheNode(final CompgraphSingleEdge<OpsTile<I>> in) {
		super(in);
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

				final TilingBulkRequestable<I, I> bulk = new TilingBulkRequestable<>(inHandle.inner(), null, null);

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
		}, inHandle.getGridDims(), inHandle.getTileDims());
	}

}
