
package experimental.tiling;

import net.imglib2.Interval;
import net.imglib2.RandomAccessibleInterval;

public interface Tile<T> extends RandomAccessibleInterval<T> {

	int[] getIndex();

	long[] getOverlap();

	Interval getValidInterval();
}
