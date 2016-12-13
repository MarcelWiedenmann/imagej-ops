
package experimental.compgraph.tiling;

import java.util.function.Function;

import net.imglib2.RandomAccessibleInterval;

import experimental.compgraph.AbstractCompgraphUnaryNode;
import experimental.compgraph.CompgraphSingleEdge;
import experimental.compgraph.node.Map;
import experimental.compgraph.request.IntervalTransformRequest;
import experimental.compgraph.request.RequestableRai;

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
	protected TilingDataHandle<O> applyInternal(final TilingDataHandle<I> inData) {
		return new TilingDataHandle<O>() {

			@Override
			public RequestableRai<O> inner() {
				return new RequestableRai<O>() {

					@Override
					public RandomAccessibleInterval<O> request(final Iterable<IntervalTransformRequest> requests) {
						final RandomAccessibleInterval<I> i = inData.inner().request(requests);
						return f.apply(i);
					}
				};
			}
		};
	}

	// -- Map --

	@Override
	public Function<? super RandomAccessibleInterval<I>, RandomAccessibleInterval<O>> func() {
		return f;
	}
}
