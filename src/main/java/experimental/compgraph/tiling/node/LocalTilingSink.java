
package experimental.compgraph.tiling.node;

import net.imglib2.RandomAccessibleInterval;
import net.imglib2.img.basictypeaccess.volatiles.VolatileFloatAccess;
import net.imglib2.type.numeric.real.FloatType;

import bdv.viewer.render.CompGraphImg;
import experimental.cache.loader.CompGraphImgLoader;
import experimental.compgraph.AbstractCompgraphSinkNode;
import experimental.compgraph.CompgraphEdge;
import experimental.compgraph.tiling.TilingDataHandle;
import experimental.compgraph.tiling.request.TilingBulkRequestable;
import experimental.compgraph.tiling.request.TilingRequestable;

public class LocalTilingSink<IO> extends
		AbstractCompgraphSinkNode<RandomAccessibleInterval<IO>, TilingDataHandle<IO>, CompGraphImg<FloatType, VolatileFloatAccess>> {

	public LocalTilingSink(final CompgraphEdge<RandomAccessibleInterval<IO>> in) {
		super(in);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected CompGraphImg<FloatType, VolatileFloatAccess> getInternal(final TilingDataHandle<IO> inData) {
		// TODO just casting for now... we can assume that output is floattype..
		final CompGraphImgLoader<FloatType> loader = new CompGraphImgLoader<>(
				new TilingBulkRequestable<FloatType, FloatType>((TilingRequestable<FloatType>) inData.inner(),
						inData.getGridDims(), inData.getTileDims()));
		return (CompGraphImg<FloatType, VolatileFloatAccess>) loader.getSetupImgLoader(0).getImage(0);
	}
}
