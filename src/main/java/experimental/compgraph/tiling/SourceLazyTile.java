
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

	private long[] globalMin;

	private long[] globalMax;

	public SourceLazyTile(final RandomAccessibleInterval<I> source, final Tile tile, final long[] gridPos) {
		super(tile);
		this.source = source;
		this.hashCode = hashCode() * 31 + 1;
		this.tile = tile;

		// TODO avoid object creation!!!!
		this.globalMin = new long[gridPos.length];
		this.globalMax = new long[gridPos.length];

		for (int i = 0; i < globalMin.length; i++) {
			globalMin[i] = tile.min(i) + (tile.dimension(i) * gridPos[i]);
			globalMax[i] = tile.max(i) + (tile.dimension(i) * gridPos[i]);
		}

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
			o = Views.interval(source, globalMin, globalMax);
			cache.put(hashCode ^ tile.flatIndex(), o);
		}

		return o;
	}
}
