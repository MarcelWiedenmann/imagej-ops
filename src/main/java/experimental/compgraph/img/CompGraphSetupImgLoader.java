package experimental.compgraph.img;

import net.imglib2.RandomAccessibleInterval;
import net.imglib2.img.basictypeaccess.volatiles.array.VolatileFloatArray;
import net.imglib2.type.NativeType;
import net.imglib2.type.numeric.RealType;
import net.imglib2.type.numeric.real.FloatType;
import net.imglib2.util.Fraction;

import bdv.cache.CacheHints;
import bdv.cache.LoadingStrategy;
import bdv.img.cache.VolatileGlobalCellCache;
import bdv.img.cache.VolatileImgCells;
import bdv.img.cache.VolatileImgCells.CellCache;
import bdv.viewer.render.CompGraphFloatArrayLoader;
import bdv.viewer.render.CompGraphImg;
import experimental.compgraph.tiling.request.TilingBulkRequestable;
import mpicbg.spim.data.generic.sequence.BasicSetupImgLoader;
import mpicbg.spim.data.generic.sequence.ImgLoaderHint;

public class CompGraphSetupImgLoader<T extends NativeType<T> & RealType<T>> implements BasicSetupImgLoader<T> {

	private CompGraphFloatArrayLoader<T> floatLoader;
	private VolatileGlobalCellCache cache;
	private TilingBulkRequestable<T, T> bulk;

	public CompGraphSetupImgLoader(final TilingBulkRequestable<T, T> bulk) {
		this.bulk = bulk;
	}

	@Override
	public RandomAccessibleInterval<T> getImage(int timepointId, ImgLoaderHint... hints) {
		floatLoader = new DefaultCompGraphFloatArrayLoader<>(bulk);
		cache = new VolatileGlobalCellCache(1, 1);

		final CellCache<VolatileFloatArray> c = cache.new VolatileCellCache<>(0, 0, 0,
				new CacheHints(LoadingStrategy.BLOCKING, 0, false), floatLoader);

		// TODO this is not really correct, as we make the image always a bit
		// bigger (potentially...)
		long[] gridDims = bulk.getGridDims();
		int[] tileDims = bulk.getTileDims();
		long[] globalDims = new long[gridDims.length];
		for (int d = 0; d < tileDims.length; d++) {
			globalDims[d] = tileDims[d] * gridDims[d];
		}

		final VolatileImgCells<VolatileFloatArray> cells = new VolatileImgCells<>(c, new Fraction(), globalDims,
				tileDims);

		return new CompGraphImg<>(cells, c, floatLoader);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getImageType() {
		// TODO hack. At some point we have to be more general
		return (T) new FloatType();
	}

}
