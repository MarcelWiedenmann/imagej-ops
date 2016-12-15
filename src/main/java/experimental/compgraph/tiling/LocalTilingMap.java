
package experimental.compgraph.tiling;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import net.imglib2.RandomAccessibleInterval;

import experimental.compgraph.AbstractCompgraphUnaryNode;
import experimental.compgraph.CompgraphSingleEdge;
import experimental.compgraph.node.Map;
import experimental.compgraph.request.Tile;
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

		// global mask
		// properties
		this.mask = new TilingMask();
	}

	// -- AbstractCompgraphUnaryNode --

	@Override
	protected TilingDataHandle<O> applyInternal(final TilingDataHandle<I> inHandle) {
		return new TilingDataHandle<>(new TilingRequestable<O>() {

			@Override
			public LazyTile<O> request(final TileRequest requests) {
				final TilingRequestable<I> inner = inHandle.inner();

				List<LazyTile<O>> mask(requests.key(), fAsInvertible, requestable);
				
				for (Tile tile : requests) {

					// goal one forward requests to requestable and receive
					// something back!

					ContextualLazyTile ctx = mask.require(key, );

					// (a) smart way to do it

					ContextualLazyTile ctx = new ContextualLazyTile(key, mask);
				}
				// (b) NAIVE WAY TO DO IT
				// final ArrayList<Tile> list = new ArrayList<>();
				// for (final Tile t : mask) {
				// list.add(inner.request((TileRequest) null));
				// }
				//
				// list to LazyTile

				return ctx;
			}
		});
	}

	// -- Map --
	@Override
	public Function<? super RandomAccessibleInterval<I>, RandomAccessibleInterval<O>> func() {
		return f;
	}
}
