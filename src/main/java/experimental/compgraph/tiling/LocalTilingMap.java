
package experimental.compgraph.tiling;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import net.imglib2.Interval;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.util.Pair;
import net.imglib2.util.ValuePair;
import net.imglib2.view.Views;
import net.imglib2.view.experimental.CombinedView;

import experimental.compgraph.AbstractCompgraphUnaryNode;
import experimental.compgraph.CompgraphSingleEdge;
import experimental.compgraph.node.Map;
import experimental.compgraph.request.Tile;
import experimental.compgraph.request.TilesRequest;
import experimental.compgraph.request.TilingRequestable;
import experimental.compgraph.request.UnaryInvertibleIntervalFunction;

public class LocalTilingMap<I, O> extends
		AbstractCompgraphUnaryNode<RandomAccessibleInterval<I>, TilingDataHandle<I>, RandomAccessibleInterval<O>, TilingDataHandle<O>>
		implements
		Map<RandomAccessibleInterval<I>, TilingDataHandle<I>, RandomAccessibleInterval<O>, TilingDataHandle<O>>,
		TilingUnaryNode<I, O> {

	private final Function<? super RandomAccessibleInterval<I>, RandomAccessibleInterval<O>> f;

	public LocalTilingMap(final CompgraphSingleEdge<RandomAccessibleInterval<I>> in,
			final Function<? super RandomAccessibleInterval<I>, RandomAccessibleInterval<O>> f) {
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
				final TilingBulkRequestable<I, O> bulk = new TilingBulkRequestable<>(inHandle.inner());

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
				TilingMask<I> mask = new TilingMask<>(bulk.flush(), null, null);

				final CombinedView<I> view = new CombinedView<>(Views.interval(mask, gridDims));

				final List<LazyTile<O>> results = new ArrayList<>();
				for (final Pair<Tile, Interval> i : queue) {
					results.add(new DefaultLazyTile<>(f, Views.interval(view, i.getB()), i.getA()));
				}

				queue.clear();

				return results.iterator();
			}
		});
	}

	// -- Map --

	@Override
	public Function<? super RandomAccessibleInterval<I>, RandomAccessibleInterval<O>> func() {
		return f;
	}
}
