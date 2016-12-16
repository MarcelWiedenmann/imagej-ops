
package experimental.compgraph.tiling;

import java.util.function.Function;

import net.imglib2.AbstractInterval;
import net.imglib2.Interval;
import net.imglib2.RandomAccess;
import net.imglib2.RandomAccessibleInterval;

import org.scijava.cache.CacheService;

import experimental.cache.loader.CacheHack;
import experimental.compgraph.channel.collection.img.OpsTile;

public class DefaultLazyTile<I, O> extends AbstractInterval implements LazyTile<O> {

	private CacheService cache = CacheHack.getCacheService();

	private Function<? super OpsTile<I>, OpsTile<O>> func;

	private OpsTile<I> source;

	private Tile tile;

	private int hashCode;

	public DefaultLazyTile(final Function<? super OpsTile<I>, OpsTile<O>> func, final OpsTile<I> source,
			final Tile tile) {
		super(tile);
		this.func = func;
		this.source = source;
		this.hashCode = hashCode() * 31 + 1;
		this.tile = tile;
	}

	// -- RandomAccessibleInterval --
	@Override
	public RandomAccess<O> randomAccess() {
		return get().randomAccess();
	}

	@Override
	public RandomAccessibleInterval<O> get() {
		@SuppressWarnings("unchecked")
		OpsTile<O> o = (OpsTile<O>) cache.get(((hashCode * 31) ^ tile.flatIndex()));

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
