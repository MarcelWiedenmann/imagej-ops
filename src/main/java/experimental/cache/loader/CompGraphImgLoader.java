package experimental.cache.loader;

import net.imglib2.type.NativeType;

import experimental.compgraph.tiling.Tiling;
import mpicbg.spim.data.generic.sequence.BasicImgLoader;
import mpicbg.spim.data.generic.sequence.BasicSetupImgLoader;

public class CompGraphImgLoader<T extends NativeType<T>> implements BasicImgLoader {

	private Tiling<T> tiling;

	public CompGraphImgLoader(Tiling<T> tiling) {
		this.tiling = tiling;
	}

	@Override
	public BasicSetupImgLoader<?> getSetupImgLoader(final int setupId) {
		return new CompGraphSetupImgLoader<>(tiling);
	}

}
