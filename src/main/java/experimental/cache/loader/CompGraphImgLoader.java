package experimental.cache.loader;

import net.imglib2.type.NativeType;
import net.imglib2.type.numeric.RealType;

import experimental.compgraph.tiling.request.TilingBulkRequestable;
import mpicbg.spim.data.generic.sequence.BasicImgLoader;
import mpicbg.spim.data.generic.sequence.BasicSetupImgLoader;

public class CompGraphImgLoader<T extends NativeType<T> & RealType<T>> implements BasicImgLoader {

	private TilingBulkRequestable<?, T> bulk;
	private long[] dimensions;
	private int[] tileSize;

	public CompGraphImgLoader(final TilingBulkRequestable<?, T> bulk, final int[] tileSize, final long[] dimensions) {
		this.tileSize = tileSize;
		this.dimensions = dimensions;
		this.bulk = bulk;
	}

	@Override
	public BasicSetupImgLoader<?> getSetupImgLoader(final int setupId) {
		return new CompGraphSetupImgLoader<>(bulk, dimensions, tileSize);
	}

}
