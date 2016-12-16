package experimental.cache.loader;

import net.imglib2.RandomAccessibleInterval;
import net.imglib2.img.basictypeaccess.volatiles.array.VolatileFloatArray;
import net.imglib2.type.NativeType;
import net.imglib2.util.Fraction;
import net.imglib2.util.Intervals;

import bdv.cache.CacheHints;
import bdv.cache.LoadingStrategy;
import bdv.img.cache.CachedCellImg;
import bdv.img.cache.VolatileGlobalCellCache;
import bdv.img.cache.VolatileImgCells;
import bdv.img.cache.VolatileImgCells.CellCache;
import experimental.compgraph.tiling.Tiling;
import mpicbg.spim.data.generic.sequence.BasicSetupImgLoader;
import mpicbg.spim.data.generic.sequence.ImgLoaderHint;

public class CompGraphSetupImgLoader<T extends NativeType<T>> implements BasicSetupImgLoader<T> {

	private CompGraphFloatArrayLoader<T> floatLoader;
	private VolatileGlobalCellCache cache;
	private Tiling<T> tiling;

	public CompGraphSetupImgLoader(final Tiling<T> tiling) {
		this.tiling = tiling;
	}

	@Override
	public RandomAccessibleInterval<T> getImage(int timepointId, ImgLoaderHint... hints) {
		floatLoader = new CompGraphFloatArrayLoader<>();
		cache = new VolatileGlobalCellCache(1, 1);

		final long[] dimensions = Intervals.dimensionsAsLongArray(tiling);
		final int[] tileDimensions = tiling.tileDimensions();

		final CacheHints cacheHints = new CacheHints(LoadingStrategy.BUDGETED, 0, false);

		final CellCache<VolatileFloatArray> c = cache.new VolatileCellCache<>(0, 0, 0, cacheHints, floatLoader);

		final VolatileImgCells<VolatileFloatArray> cells = new VolatileImgCells<>(c, new Fraction(), dimensions,
				tileDimensions);

		return new CachedCellImg<>(cells);
	}

	@Override
	public T getImageType() {
		return null;
	}

}
