
package experimental.compgraph.tiling;

import java.util.function.Function;

import net.imglib2.AbstractInterval;
import net.imglib2.Interval;
import net.imglib2.RandomAccess;
import net.imglib2.RandomAccessibleInterval;

import org.scijava.cache.CacheService;

public class DefaultLazyTile<I, O> extends AbstractInterval implements LazyTile<O> {

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
		return getObject().randomAccess();
	}

	public RandomAccessibleInterval<O> getObject() {
		@SuppressWarnings("unchecked")
		RandomAccessibleInterval<O> o = (RandomAccessibleInterval<O>) cache.get(((hashCode * 31) ^ tile.flatIndex()));

		if (o == null) {
			o = func.apply(source);
			cache.put(hashCode ^ tile.flatIndex(), o);
		}

		return o;
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

	@Override
	public long[] min() {
		return tile.min();
	}

	@Override
	public long[] max() {
		return tile.max();
	}
}
