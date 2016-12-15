
package experimental.compgraph.tiling;

import net.imglib2.RandomAccessibleInterval;

import experimental.compgraph.AbstractCompgraphSourceNode;
import experimental.compgraph.request.TileRequest;
import experimental.compgraph.request.TilingRequestable;

public class LocalTilingSource<IO> extends
	AbstractCompgraphSourceNode<RandomAccessibleInterval<IO>, TilingDataHandle<IO>> implements TilingOutputNode<IO>
{

	public LocalTilingSource(final RandomAccessibleInterval<? extends RandomAccessibleInterval<IO>> inData) {
		super(createDataHandle(inData));
	}

	private static <T> TilingDataHandle<T> createDataHandle(
		final RandomAccessibleInterval<? extends RandomAccessibleInterval<T>> inData)
	{
		return new TilingDataHandle<T>(new TilingRequestable<T>() {

			@Override
			public LazyTile<T> request(final TileRequest request) {
				// TODO Auto-generated method stub
				return null;
			}
		});
	}
}
