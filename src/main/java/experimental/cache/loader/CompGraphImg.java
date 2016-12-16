package experimental.cache.loader;

import net.imglib2.img.basictypeaccess.volatiles.VolatileAccess;
import net.imglib2.img.cell.AbstractCellImg;
import net.imglib2.img.cell.CellImgFactory;
import net.imglib2.type.NativeType;
import net.imglib2.type.numeric.RealType;

import bdv.cache.CacheHints;
import bdv.img.cache.CachedCellImg;
import bdv.img.cache.VolatileCell;
import bdv.img.cache.VolatileImgCells;
import bdv.img.cache.VolatileImgCells.CellCache;

public class CompGraphImg<T extends NativeType<T> & RealType<T>, A extends VolatileAccess>
		extends AbstractCellImg<T, A, VolatileCell<A>, CellImgFactory<T>> {

	private final CellCache<A> cache;
	private CompGraphFloatArrayLoader<T> loader;

	public CompGraphImg(final VolatileImgCells<A> cells, CellCache<A> cache,
			final CompGraphFloatArrayLoader<T> loader) {
		super(null, cells);
		this.cache = cache;
		this.loader = loader;
	}

	public void setCacheHints(final CacheHints cacheHints) {
		cache.setCacheHints(cacheHints);
	}

	@Override
	public CachedCellImg<T, A> copy() {
		throw new UnsupportedOperationException();
	}

	public CompGraphFloatArrayLoader<T> getLoader() {
		return loader;
	}
}
