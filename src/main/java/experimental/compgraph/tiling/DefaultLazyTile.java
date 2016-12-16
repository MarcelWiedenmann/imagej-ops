
package experimental.compgraph.tiling;

import java.util.function.Function;

import net.imglib2.AbstractInterval;
import net.imglib2.Interval;
import net.imglib2.RandomAccess;
import net.imglib2.RandomAccessibleInterval;

import org.scijava.cache.CacheService;

public class DefaultLazyTile<I, O> extends AbstractInterval implements LazyTile<O> {

	// TODO: this is where we do threading
	// TODO: partial tiles have to be handled here!

	private Function<? super RandomAccessibleInterval<I>, RandomAccessibleInterval<O>> func;

	private RandomAccessibleInterval<I> source;

	private Tile tile;

	private CacheService cache;

	private int hashCode;

	public DefaultLazyTile(final Function<? super RandomAccessibleInterval<I>, RandomAccessibleInterval<O>> func,
			final RandomAccessibleInterval<I> source, final Tile tile, final CacheService cache) {
		super(tile);
		this.cache = cache;
		this.func = func;
		this.source = source;
		this.hashCode = hashCode() * 31 + 1;
		this.tile = tile;
	}

	// -- RandomAccessibleInterval --
	@Override
	public RandomAccess<O> randomAccess() {
		@SuppressWarnings("unchecked")
		final RandomAccessibleInterval<O> o = (RandomAccessibleInterval<O>) cache
				.get(((hashCode + 1) * 31) ^ tile.flatIndex());

		return (o == null) ? func.apply(source).randomAccess() : o.randomAccess();
	}

	@Override
	public RandomAccess<O> randomAccess(final Interval interval) {
		return randomAccess();
	}

	// -- Tile --

	@Override
	public long flatIndex() {
		return tile.flatIndex();
	}
}
