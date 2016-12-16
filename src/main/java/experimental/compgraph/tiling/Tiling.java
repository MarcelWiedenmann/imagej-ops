package experimental.compgraph.tiling;

import net.imglib2.RandomAccessibleInterval;

import experimental.compgraph.request.Tile;

public interface Tiling<T> extends RandomAccessibleInterval<T> {

	Tile getTileAt(long idx);

	int[] tileDimensions();

	TilingMask<T> getMask();
}
