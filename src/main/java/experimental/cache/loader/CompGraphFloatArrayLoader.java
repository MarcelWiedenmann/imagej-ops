package experimental.cache.loader;

import net.imglib2.img.basictypeaccess.volatiles.array.VolatileFloatArray;
import net.imglib2.type.NativeType;

import bdv.img.cache.CacheArrayLoader;

public class CompGraphFloatArrayLoader<T extends NativeType<T>> implements CacheArrayLoader<VolatileFloatArray> {

	private VolatileFloatArray theEmptyArray;

	public CompGraphFloatArrayLoader() {
		theEmptyArray = new VolatileFloatArray(64 * 64 * 64, false);
	}

	@Override
	public int getBytesPerElement() {
		return 4;
	}

	@Override
	public VolatileFloatArray loadArray(int timepoint, int setup, int level, int[] dimensions, long[] min)
			throws InterruptedException {
		// TODO get tile from somewhere. In the best case I really get a tile
		// which is just a RAI which can be cast to FloatArray
		return null;
	}

	@Override
	public VolatileFloatArray emptyArray(int[] dimensions) {
		int numEntities = 1;
		for (int i = 0; i < dimensions.length; ++i)
			numEntities *= dimensions[i];
		if (theEmptyArray.getCurrentStorageArray().length < numEntities)
			theEmptyArray = new VolatileFloatArray(numEntities, false);
		return theEmptyArray;
	}

}
