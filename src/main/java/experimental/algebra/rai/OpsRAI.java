package experimental.algebra.rai;

import net.imglib2.RandomAccessibleInterval;

import experimental.algebra.OpsGrid;

public interface OpsRAI<T> extends RandomAccessibleInterval<T>, OpsGrid<T> {

	// TODO is unchecked OK here?
	@SuppressWarnings("unchecked")
	@Override
	NestedOpsRAI<T> reshape(long... spatialAxes);
}
