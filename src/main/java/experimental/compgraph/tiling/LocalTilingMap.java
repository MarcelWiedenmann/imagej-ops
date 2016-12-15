
package experimental.compgraph.tiling;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import net.imglib2.Interval;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.util.Pair;

import experimental.compgraph.AbstractCompgraphUnaryNode;
import experimental.compgraph.CompgraphSingleEdge;
import experimental.compgraph.node.Map;
import experimental.compgraph.request.TileRequest;
import experimental.compgraph.request.TilingRequestable;
import experimental.compgraph.request.UnaryInvertibleIntervalMapper;

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
			public LazyTile<O> request(final TileRequest requests) {

				// contains information of what is required

				// 0: not required
				// 1: required completely
				// 2: partially required
				final Interval key = requests.key();
				final TilingMask<I> mask = null;

				if (f instanceof UnaryInvertibleIntervalMapper) {
					final UnaryInvertibleIntervalMapper fAsInvertible = (UnaryInvertibleIntervalMapper) f;
					fAsInvertible.invert(key, mask);
				}

				final List<LazyTile<I>> results = new ArrayList<>();

				// TODO activator.mask() or just give a fuck.
				for (final Pair<Long, Interval> v : mask) {
					// think about "bulk-request"
					results.add(inHandle.inner().request((TileRequest) mask));
				}

				// TODO merge results from lazyTiling
				return null/* = new LazyTile(results, f); */;
			}
		});
	}

	// -- Map --
	@Override
	public Function<? super RandomAccessibleInterval<I>, RandomAccessibleInterval<O>> func() {
		return f;
	}
}
