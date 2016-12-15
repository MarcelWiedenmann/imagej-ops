
package experimental.compgraph.tiling;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import net.imglib2.Interval;
import net.imglib2.RandomAccess;
import net.imglib2.RandomAccessible;
import net.imglib2.RandomAccessibleInterval;

import experimental.compgraph.request.Tile;
import experimental.compgraph.request.TilesRequest;
import experimental.compgraph.request.TilingRequestable;
import experimental.compgraph.request.UnaryInvertibleIntervalFunction;

public class TilingMask<T> implements RandomAccessible<T> {

	HashMap<Long, Tile> tiles = new HashMap<>();

	public void mark(final Tile tile) {

	}

	public <
		I, O, F extends UnaryInvertibleIntervalFunction & Function<? super RandomAccessibleInterval<I>, RandomAccessibleInterval<O>>>
		List<LazyTile<O>> apply(final F f, final TilingRequestable<I> requestable, final TilesRequest r)
	{
		final List<Tile> key = r.key();
		for (final Tile t : key) {
			// Individual mask for each requested tile.
			final TilingActivator ta = new TilingActivator(this);
			final Interval i = f.invert(t, ta);
			// final List<Tile> req =

			// (1) for tile remember / recogn. which tiles have been activated.
			// (2) remember tile-indices and merge requests with existing requests on tile-indices
		}
		// (3) fire request for all tiles to requestable.
		// (4) Collect Requests and put the corresponding LazyTiles at the right position in the grid.
		// (5) Make view for each tile which was requested FROM me (e.g. Views.interval(this, key.getInterval()))
		// (6) return list of views (which is <I>) LazyTile<O> = <I> + 'f' -> LazyTile(View<I>, F<I,O>), see (5).
	}

	public <
		I1, I2, O, F extends BinaryInvertibleIntervalMapper & BiFunction<? super RandomAccessibleInterval<I1>, ? super RandomAccessibleInterval<I2>, RandomAccessibleInterval<O>>>
		List<LazyTile<O>> apply(final F f, final TilingRequestable<I1> requestable1,
			final TilingRequestable<I2> requestable2)
	{
		throw new UnsupportedOperationException("not yet implemented");
	}

	@Override
	public Iterator<Tile> iterator() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("not yet implemented");
	}

	@Override
	public RandomAccess<Tile> randomAccess() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("not yet implemented");
	}

	@Override
	public RandomAccess<Tile> randomAccess(final Interval interval) {
		return randomAccess();
	}
}
