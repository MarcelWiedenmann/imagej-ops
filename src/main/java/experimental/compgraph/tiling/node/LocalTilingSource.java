
package experimental.compgraph.tiling.node;

import java.util.ArrayList;
import java.util.Iterator;

import net.imglib2.RandomAccessibleInterval;
import net.imglib2.view.Views;

import experimental.compgraph.AbstractCompgraphSourceNode;
import experimental.compgraph.channel.collection.img.DefaultOpsTiling;
import experimental.compgraph.channel.collection.img.OpsTile;
import experimental.compgraph.tiling.LazyTile;
import experimental.compgraph.tiling.SourceLazyTile;
import experimental.compgraph.tiling.Tile;
import experimental.compgraph.tiling.TilingDataHandle;
import experimental.compgraph.tiling.request.TilesRequest;
import experimental.compgraph.tiling.request.TilingRequestable;

public class LocalTilingSource<IO> extends AbstractCompgraphSourceNode<OpsTile<IO>, TilingDataHandle<IO>>
		implements TilingOutputNode<IO> {

	private int[] tileDims;
	private long[] gridDims;

	public LocalTilingSource(final RandomAccessibleInterval<IO> inData, int[] tileDims) {
		super(createDataHandle(inData, tileDims));
		gridDims = new long[tileDims.length];

		for (int d = 0; d < gridDims.length; d++) {
			gridDims[d] = inData.dimension(d) / tileDims[d];
		}

		this.tileDims = tileDims;
	}

	private static <T> TilingDataHandle<T> createDataHandle(final RandomAccessibleInterval<T> inData,
			final int[] tileDims) {

		// TODO dont calc twice
		long[] gridDims = new long[tileDims.length];
		for (int d = 0; d < gridDims.length; d++) {
			gridDims[d] = inData.dimension(d) / tileDims[d];
		}

		// TODO make it more flexible etc...
		final RandomAccessibleInterval<T> extended = Views.interval(Views.extendMirrorSingle(inData), inData);
		return new TilingDataHandle<>(new TilingRequestable<T>() {

			@Override
			public Iterator<LazyTile<T>> request(final TilesRequest request) {
				final Iterator<Tile> requests = request.key();
				final ArrayList<LazyTile<T>> requesteds = new ArrayList<>();

				while (requests.hasNext()) {
					Tile t = requests.next();
					requesteds.add(new SourceLazyTile<>(extended, t));
				}
				return requesteds.iterator();
			}
		}, gridDims, tileDims);
	}

	public DefaultOpsTiling<IO> create() {
		return new DefaultOpsTiling<>(this, gridDims, tileDims);

	}
}
