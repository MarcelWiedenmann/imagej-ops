
package experimental.compgraph.tiling;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import net.imglib2.Interval;
import net.imglib2.view.IntervalView;
import net.imglib2.view.Views;

import experimental.compgraph.request.Tile;
import experimental.compgraph.request.UnaryInvertibleIntervalFunction;

public class TilingBulkRequestable<O> {

	ConcurrentHashMap<Long, Tile> tiles = new ConcurrentHashMap<>();
	private boolean isFlushed;

	void request() {
		// TODO: we probably don't want this to be a method - multiple 'apply'ing does not make sense a.t.m.
		final List<Tile> key = request.key();
		final ArrayList<LazyTile<O>> requesteds = new ArrayList<>(key.size());
		if (f instanceof UnaryInvertibleIntervalFunction) {
			final UnaryInvertibleIntervalFunction fAsInvertible = (UnaryInvertibleIntervalFunction) f;
			for (final Tile t : key) {
				// Individual mask for each requested tile.
				final TilingActivator ta = new TilingActivator(this);
				final Interval i = fAsInvertible.invert(t, ta);
				// Tiles needed ("activated") to produce our requested tile.
				final IntervalView<I> inputInterval = Views.interval(tiling, i);
				// Our requested tile.
				requesteds.add(new DefaultLazyTile<>(inputInterval, f, t));
			}
		}
		else {
			throw new UnsupportedOperationException("not yet implemented");
		}

		ready();

		return requesteds;
	}

	public void request(final long index, final Interval interval) {

	}

	public LazyTile<O> get(final long index) {
		// TODO Auto-generated method stub

	}

	public void flush() {
		if (!isFlushed) {
			// TODO
		}
	}

}
