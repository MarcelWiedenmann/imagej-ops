
package experimental.compgraph.tiling.node;

import java.util.ArrayList;
import java.util.List;

import net.imglib2.RandomAccess;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.view.Views;

import experimental.compgraph.AbstractCompgraphSourceNode;
import experimental.compgraph.tiling.DefaultLazyTile;
import experimental.compgraph.tiling.LazyTile;
import experimental.compgraph.tiling.Tile;
import experimental.compgraph.tiling.TilingDataHandle;
import experimental.compgraph.tiling.request.TilesRequest;
import experimental.compgraph.tiling.request.TilingRequestable;

public class LocalTilingSource<IO> extends
	AbstractCompgraphSourceNode<RandomAccessibleInterval<IO>, TilingDataHandle<IO>> implements TilingOutputNode<IO>
{

	public LocalTilingSource(final RandomAccessibleInterval<? extends RandomAccessibleInterval<IO>> inData) {
		super(createDataHandle(inData));
	}

	private static <T> TilingDataHandle<T> createDataHandle(
		final RandomAccessibleInterval<? extends RandomAccessibleInterval<T>> inData)
	{
		final RandomAccess<? extends RandomAccessibleInterval<T>> inDataRA = Views.extendMirrorSingle(inData)
			.randomAccess();
		return new TilingDataHandle<T>(new TilingRequestable<T>() {

			@Override
			public List<LazyTile<T>> request(final TilesRequest request) {
				final List<Tile> requests = request.key();
				final ArrayList<LazyTile<T>> requesteds = new ArrayList<>(requests.size());
				for (final Tile t : requests) {
					inDataRA.setPosition(t.index());
					requesteds.add(new DefaultLazyTile<>(inDataRA.get(), t));
				}
				return requesteds;
			}
		});
	}
}
