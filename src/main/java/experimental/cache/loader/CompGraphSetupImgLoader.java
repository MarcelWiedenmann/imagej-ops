package experimental.cache.loader;

import net.imglib2.RandomAccessibleInterval;
import net.imglib2.img.basictypeaccess.volatiles.array.VolatileFloatArray;
import net.imglib2.type.NativeType;
import net.imglib2.type.numeric.RealType;
import net.imglib2.util.Fraction;

import bdv.cache.CacheHints;
import bdv.cache.LoadingStrategy;
import bdv.img.cache.VolatileGlobalCellCache;
import bdv.img.cache.VolatileImgCells;
import bdv.img.cache.VolatileImgCells.CellCache;
import experimental.compgraph.tiling.request.TilingBulkRequestable;
import mpicbg.spim.data.generic.sequence.BasicSetupImgLoader;
import mpicbg.spim.data.generic.sequence.ImgLoaderHint;

public class CompGraphSetupImgLoader<T extends NativeType<T> & RealType<T>> implements BasicSetupImgLoader<T> {

	private CompGraphFloatArrayLoader<T> floatLoader;
	private VolatileGlobalCellCache cache;
	private TilingBulkRequestable<?, T> bulk;

	private long[] dimensions;
	private int[] tileDimensions;

	public CompGraphSetupImgLoader(final TilingBulkRequestable<?, T> bulk, final long[] dimensions,
			final int[] tileDimensions) {
		this.bulk = bulk;
		this.tileDimensions = tileDimensions;
		this.dimensions = dimensions;
	}

	@Override
	public RandomAccessibleInterval<T> getImage(int timepointId, ImgLoaderHint... hints) {
		floatLoader = new CompGraphFloatArrayLoader<>(bulk);
		cache = new VolatileGlobalCellCache(1, 1);

		final CellCache<VolatileFloatArray> c = cache.new VolatileCellCache<>(0, 0, 0,
				new CacheHints(LoadingStrategy.BUDGETED, 0, false), floatLoader);

		final VolatileImgCells<VolatileFloatArray> cells = new VolatileImgCells<>(c, new Fraction(), dimensions,
				tileDimensions);

		return new CompGraphImg<>(cells, c, floatLoader);
	}

	@Override
	public T getImageType() {
		return null;
	}

}
