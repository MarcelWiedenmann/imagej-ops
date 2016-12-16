
package experimental.compgraph.tiling;

import java.util.function.Function;

import net.imglib2.AbstractInterval;
import net.imglib2.Interval;
import net.imglib2.Point;
import net.imglib2.RandomAccessibleInterval;

import experimental.compgraph.request.Tile;

public class DefaultLazyTile<I, O> extends AbstractInterval implements LazyTile<O> {

	// TODO: this is where we do threading
	// TODO: partial tiles have to be handled here!

	public DefaultLazyTile(final Function<? super RandomAccessibleInterval<I>, RandomAccessibleInterval<O>> f,
			final RandomAccessibleInterval<I> source, final Tile interval) {
		super(interval);
	}

	// -- RandomAccessibleInterval --

	@Override
	public DefaultLazyTileRandomAccess<T> randomAccess() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DefaultLazyTileRandomAccess<T> randomAccess(final Interval interval) {
		return randomAccess();
	}

	// -- Tile --

	@Override
	public long flatIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long[] index() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isPartialTile() {
		// TODO Auto-generated method stub
		return false;
	}

	// -- Nested Classes --

	public static class DefaultLazyTileRandomAccess<T> extends Point implements LazyTileRandomAccess<T> {

		// -- RandomAccess --

		@Override
		public T get() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public DefaultLazyTileRandomAccess<T> copy() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public DefaultLazyTileRandomAccess<T> copyRandomAccess() {
			// TODO Auto-generated method stub
			return null;
		}
	}
}
