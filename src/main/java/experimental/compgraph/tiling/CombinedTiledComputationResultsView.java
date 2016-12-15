
package experimental.compgraph.tiling;

import net.imglib2.Interval;
import net.imglib2.RandomAccess;
import net.imglib2.view.experimental.CombinedView;

import experimental.compgraph.request.DefaultTileRequest;
import experimental.compgraph.request.TilingRequestable;

public class CombinedTiledComputationResultsView<T> extends CombinedView<T> {

	private final TiledComputationResultsView<T> source;

	public CombinedTiledComputationResultsView(final TiledComputationResultsView<T> source) {
		super(source);
		this.source = source;
	}

	// -- CombinedView --

	@Override
	public CombinedViewRandomAccess<T> randomAccess(final Interval interval) {

		return new CombinedTiledComputationResultsRandomAccess<>(source, blockSize, interval);
	}

	// -- Nested Class --

	public static class CombinedTiledComputationResultsRandomAccess<T> extends CombinedViewRandomAccess<T> {

		private final TilingRequestable<T> supplier;
		private Interval interval;
		private boolean intervalChanged;
		private LazyTile<T> requested;
		private RandomAccess<T> requestedRA;

		public CombinedTiledComputationResultsRandomAccess(final TiledComputationResultsView<T> source,
			final long[] blockSize, final Interval interval)
		{
			super(source, blockSize);
			this.supplier = source.getSupplier();
			updateInterval(interval);
		}

		public CombinedTiledComputationResultsRandomAccess(final CombinedTiledComputationResultsRandomAccess<T> ra) {
			super(ra);
			this.supplier = ra.supplier;
			updateInterval(ra.interval);
		}

		public void updateInterval(final Interval interval) {
			this.interval = interval;
			intervalChanged = true;
		}

		// -- CombinedViewRandomAccess --

		@Override
		public T get() {

			// TODO: we might need a more lightweight alternative to creating new request objects every time our interval
			// changes. however, note that - right now and until we gain more insight w.r.t. threading etc. - requests are to
			// be considered immutable.
			if (intervalChanged) {
				requested = supplier.request(new DefaultTileRequest(interval));
				requestedRA = requested.randomAccess();
			}
			// !
			// TODO / FIXME: offset? where is the origin of the requested interval?
			// !
			requestedRA.setPosition(position);
			return requestedRA.get();
		}

		@Override
		public CombinedTiledComputationResultsRandomAccess<T> copy() {
			return new CombinedTiledComputationResultsRandomAccess<>(this);
		}

		@Override
		public CombinedTiledComputationResultsRandomAccess<T> copyRandomAccess() {
			return copy();
		}
	}
}
