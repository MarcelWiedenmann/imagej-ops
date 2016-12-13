
package experimental.compgraph.tiling;

import net.imglib2.RandomAccessibleInterval;

import experimental.compgraph.AbstractCompgraphSourceNode;
import experimental.compgraph.request.IntervalTransformRequest;
import experimental.compgraph.request.RequestableRai;

public class LocalTilingSource<T> extends AbstractCompgraphSourceNode<RandomAccessibleInterval<T>, TilingDataHandle<T>>
	implements TilingOutputNode<T>
{

	public LocalTilingSource(final RandomAccessibleInterval<? extends RandomAccessibleInterval<T>> inData) {
		super(createDataHandle(inData));
	}

	private static <T> TilingDataHandle<T> createDataHandle(
		final RandomAccessibleInterval<? extends RandomAccessibleInterval<T>> inData)
	{
		return new TilingDataHandle<T>() {

			@Override
			public RequestableRai<T> inner() {
				return new RequestableRai<T>() {

					@Override
					public RandomAccessibleInterval<T> request(final Iterable<IntervalTransformRequest> requests) {

						// TODO: return those tiles which match the requested intervals (and wrap in view).

						return null;
					}
				};
			}
		};
	}
}
