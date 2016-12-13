
package experimental.compgraph.tiling;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import net.imglib2.RandomAccessibleInterval;
import net.imglib2.view.Views;

import experimental.compgraph.AbstractCompgraphUnaryNode;
import experimental.compgraph.CompgraphSingleEdge;
import experimental.compgraph.node.Map;
import experimental.compgraph.request.BinaryInvertibleIntervalMapper;
import experimental.compgraph.request.DefaultIntervalRequest;
import experimental.compgraph.request.IntervalRequest;
import experimental.compgraph.request.TilingRequestable;
import experimental.compgraph.request.UnaryInvertibleIntervalMapper;

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
		return new TilingDataHandle<O>(new TilingRequestable<O>() {

			@Override
			public List<RandomAccessibleInterval<O>> request(final List<IntervalRequest> requests) {
				final List<IntervalRequest> myRequests;
				if (f instanceof UnaryInvertibleIntervalMapper) {
					myRequests = new ArrayList<>(requests.size());
					final UnaryInvertibleIntervalMapper fAsInvertible = (UnaryInvertibleIntervalMapper) f;
					for (final IntervalRequest req : requests) {
						myRequests.add(new DefaultIntervalRequest(fAsInvertible.invert(req.key())));
					}
				}
				else if (f instanceof BinaryInvertibleIntervalMapper) {
					throw new UnsupportedOperationException("not yet implemented");
				}
				else {
					myRequests = requests;
				}
				final List<RandomAccessibleInterval<I>> in = inHandle.inner().request(myRequests);

				assert myRequests.size() == requests.size();
				assert myRequests.size() == in.size();

				final List<RandomAccessibleInterval<O>> results = new ArrayList<>(requests.size());
				if (f instanceof UnaryInvertibleIntervalMapper) {
					for (int i = 0; i < in.size(); i++) {
						final RandomAccessibleInterval<O> res = Views.interval(f.apply(in.get(i)), myRequests.get(i).key());
						results.add(res);
					}
				}
				else {
					for (final RandomAccessibleInterval<I> i : in) {
						results.add(f.apply(i));
					}
				}

				assert results.size() == in.size();

				return results;
			}
		});
	}

	// -- Map --

	@Override
	public Function<? super RandomAccessibleInterval<I>, RandomAccessibleInterval<O>> func() {
		return f;
	}
}
