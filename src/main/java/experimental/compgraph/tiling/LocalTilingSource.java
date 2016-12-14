
package experimental.compgraph.tiling;

import java.util.List;

import net.imglib2.RandomAccessibleInterval;

import experimental.compgraph.AbstractCompgraphSourceNode;
import experimental.compgraph.request.TileRequest;
import experimental.compgraph.request.TilingRequestable;

public class LocalTilingSource<IO> extends AbstractCompgraphSourceNode<RandomAccessibleInterval<IO>, TilingDataHandle<IO>>
	implements TilingOutputNode<IO>
{

	public LocalTilingSource(final RandomAccessibleInterval<? extends RandomAccessibleInterval<IO>> inData) {
		super(createDataHandle(inData));
	}

	private static <T> TilingDataHandle<T> createDataHandle(
		final RandomAccessibleInterval<? extends RandomAccessibleInterval<T>> inData)
	{
		return new TilingDataHandle<T>(new TilingRequestable<T>() {

			@Override
			public List<RandomAccessibleInterval<T>> request(final List<TileRequest> requests) {

				// TODO: return those tiles which match the requested intervals (and wrap in view).

				return null;
			}
		});
	}
}
