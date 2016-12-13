
package experimental.compgraph.tiling;

import java.util.List;

import net.imglib2.RandomAccessibleInterval;

import experimental.compgraph.AbstractCompgraphSourceNode;
import experimental.compgraph.request.IntervalRequest;
import experimental.compgraph.request.TilingRequestable;

public class LocalTilingSource<T> extends AbstractCompgraphSourceNode<RandomAccessibleInterval<T>, TilingDataHandle<T>>
	implements TilingOutputNode<T>
{

	public LocalTilingSource(final RandomAccessibleInterval<? extends RandomAccessibleInterval<T>> inData) {
		super(createDataHandle(inData));
	}

	private static <T> TilingDataHandle<T> createDataHandle(
		final RandomAccessibleInterval<? extends RandomAccessibleInterval<T>> inData)
	{
		return new TilingDataHandle<T>(new TilingRequestable<T>() {

			@Override
			public List<RandomAccessibleInterval<T>> request(final List<IntervalRequest> requests) {

				// TODO: return those tiles which match the requested intervals (and wrap in view).

				return null;
			}
		});
	}
}
