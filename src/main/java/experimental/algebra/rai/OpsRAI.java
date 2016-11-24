package experimental.algebra.rai;

import java.util.function.Function;

import net.imglib2.RandomAccessibleInterval;

import experimental.algebra.OpsCollection;
import experimental.algebra.OpsGrid;

public interface OpsRAI<T> extends RandomAccessibleInterval<T>, OpsGrid<T> {

	// filters a grid such that only grid coordinates within the interval are
	// left
	// OpsGrid<T> filter(Interval val);

	// scatter on pixels
	OpsTiling<T> tiling(TilingHints... hints);

}
