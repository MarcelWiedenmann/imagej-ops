
package experimental.compgraph.tiling;

import net.imglib2.AbstractInterval;
import net.imglib2.Interval;
import net.imglib2.RandomAccess;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.view.Views;

import org.scijava.cache.CacheService;

import experimental.compgraph.service.CompgraphCache;

public class SourceLazyTile<I> extends AbstractInterval implements LazyTile<I> {

	private CacheService cache = CompgraphCache.getCacheService();

	private RandomAccessibleInterval<I> source;

	private Tile tile;

	private int hashCode;

	public SourceLazyTile(final RandomAccessibleInterval<I> source, final Tile tile) {
		super(tile);
		this.source = source;
		this.hashCode = hashCode() * 31 + 1;
		this.tile = tile;
	}

	// -- RandomAccessibleInterval --
	@Override
	public RandomAccess<I> randomAccess() {
		return get().randomAccess();
	}

	@Override
	public RandomAccess<I> randomAccess(final Interval interval) {
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

	@Override
	public RandomAccessibleInterval<I> get() {
		@SuppressWarnings("unchecked")
		RandomAccessibleInterval<I> o = (RandomAccessibleInterval<I>) cache.get(((hashCode * 31) ^ tile.flatIndex()));

		if (o == null) {
			o = Views.interval(source, this);
			cache.put(hashCode ^ tile.flatIndex(), o);
		}

		return o;
	}
}
