
package experimental.compgraph.tiling;

import java.util.List;
import java.util.function.Function;

import net.imglib2.RandomAccessibleInterval;

import experimental.compgraph.AbstractCompgraphUnaryNode;
import experimental.compgraph.CompgraphSingleEdge;
import experimental.compgraph.node.Map;
import experimental.compgraph.request.TilesRequest;
import experimental.compgraph.request.TilingRequestable;

public class LocalTilingMap<I, O> extends
	AbstractCompgraphUnaryNode<RandomAccessibleInterval<I>, TilingDataHandle<I>, RandomAccessibleInterval<O>, TilingDataHandle<O>>
	implements Map<RandomAccessibleInterval<I>, TilingDataHandle<I>, RandomAccessibleInterval<O>, TilingDataHandle<O>>,
	TilingUnaryNode<I, O>
{

	private final Function<? super RandomAccessibleInterval<I>, RandomAccessibleInterval<O>> f;

	public LocalTilingMap(final CompgraphSingleEdge<RandomAccessibleInterval<I>> in,
		final Function<? super RandomAccessibleInterval<I>, RandomAccessibleInterval<O>> f)
	{
		super(in);
		this.f = f;
	}

	// -- AbstractCompgraphUnaryNode --

	@Override
	protected TilingDataHandle<O> applyInternal(final TilingDataHandle<I> inHandle) {
		return new TilingDataHandle<>(new TilingRequestable<O>() {

			@Override
			public List<LazyTile<O>> request(final TilesRequest request) {
				final TilingRequestable<I> requestable = inHandle.inner();
				final List<LazyTile<O>> requesteds = request.apply(f, requestable);
				return requesteds;
			}
		});
	}

	// -- Map --

	@Override
	public Function<? super RandomAccessibleInterval<I>, RandomAccessibleInterval<O>> func() {
		return f;
	}
}
