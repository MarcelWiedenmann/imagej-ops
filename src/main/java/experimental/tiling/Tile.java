
package experimental.tiling;

import net.imglib2.Interval;

// TODO: think of other tile types than RAI (text tile, geospatial data etc). exposes element-info (T).

public interface Tile<T> extends DistributedGrid<T> {

	int[] getIndex();

	long[] getOverlap();

	Interval getValidInterval();
}
