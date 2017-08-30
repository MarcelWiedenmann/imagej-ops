
package experimental.compgraph.tiling.node;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import net.imglib2.FinalInterval;
import net.imglib2.Interval;
import net.imglib2.util.Pair;
import net.imglib2.util.ValuePair;
import net.imglib2.view.Views;
import net.imglib2.view.experimental.CombinedView;

import experimental.compgraph.AbstractCompgraphUnaryNode;
import experimental.compgraph.CompgraphSingleEdge;
import experimental.compgraph.channel.collection.img.DefaultOpsTile;
import experimental.compgraph.channel.collection.img.OpsTile;
import experimental.compgraph.node.Map;
import experimental.compgraph.request.UnaryInvertibleIntervalFunction;
import experimental.compgraph.tiling.DefaultLazyTile;
import experimental.compgraph.tiling.LazyTile;
import experimental.compgraph.tiling.Tile;
import experimental.compgraph.tiling.TilingDataHandle;
import experimental.compgraph.tiling.TilingMask;
import experimental.compgraph.tiling.request.TilesRequest;
import experimental.compgraph.tiling.request.TilingActivator;
import experimental.compgraph.tiling.request.TilingBulkRequestable;
import experimental.compgraph.tiling.request.TilingRequestable;

public class LocalTilingMapNode<I, O>
		extends AbstractCompgraphUnaryNode<OpsTile<I>, TilingDataHandle<I>, OpsTile<O>, TilingDataHandle<O>>
		implements Map<OpsTile<I>, TilingDataHandle<I>, OpsTile<O>, TilingDataHandle<O>>, TilingUnaryNode<I, O> {

	private final Function<? super OpsTile<I>, OpsTile<O>> f;

	public LocalTilingMapNode(final CompgraphSingleEdge<OpsTile<I>> in,
			final Function<? super OpsTile<I>, OpsTile<O>> f) {
		super(in);
		this.f = f;
	}

	// -- AbstractCompgraphUnaryNode --

	@Override
	protected TilingDataHandle<O> applyInternal(final TilingDataHandle<I> inHandle) {
		return new TilingDataHandle<>(new TilingRequestable<O>() {

			@Override
			public Iterator<LazyTile<O>> request(final TilesRequest request) {

				// TODO maybe the both are actually the same ..
				final TilingBulkRequestable<I, O> bulk = new TilingBulkRequestable<>(inHandle.inner(),
						inHandle.getGridDims(), inHandle.getTileDims());
				final TilingActivator act = new TilingActivator(bulk);

				// TODO maybe list of pairs is more funny
				final ArrayList<Pair<Tile, Interval>> queue = new ArrayList<>();

				final Iterator<Tile> reqIt = request.key();
				while (reqIt.hasNext()) {
					final Tile tile = reqIt.next();
					if (f instanceof UnaryInvertibleIntervalFunction) {
						queue.add(new ValuePair<>(tile, ((UnaryInvertibleIntervalFunction<?, ?>) f).invert(tile, act)));
					} else {
						throw new UnsupportedOperationException("");
					}
				}
				// TODO need tile-size, tiling-dims and grid dims!!!
				// TODO efficiency? ;-)
				final TilingMask<I> mask = new TilingMask<>(bulk.flush(), inHandle.getGridDims(),
						inHandle.getTileDims());

				final CombinedView<I> view = new CombinedView<>(
						Views.interval(mask, new FinalInterval(inHandle.getGridDims())));

				final List<LazyTile<O>> results = new ArrayList<>();
				for (final Pair<Tile, Interval> i : queue) {
					// TODO object creation?! wrap, wrap, wrap
					results.add(
							new DefaultLazyTile<>(f, new DefaultOpsTile<>(Views.interval(view, i.getB())), i.getA()));
				}
				queue.clear();
				return results.iterator();
			}
		}, inHandle.getGridDims(), inHandle.getTileDims());
	}

	// -- Map --

	@Override
	public Function<? super OpsTile<I>, OpsTile<O>> func() {
		return f;
	}
}
