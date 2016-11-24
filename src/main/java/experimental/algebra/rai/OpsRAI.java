package experimental.algebra.rai;

import net.imglib2.RandomAccessibleInterval;

import experimental.algebra.OpsGrid;

public interface OpsRAI<T> extends RandomAccessibleInterval<T>, OpsGrid<T> {

	// Internally calls partition on itself (just as it would be in a collection
	// C<I> on which partition would have been called.
	OpsTiling<T> tiling(TilingHints hints);
}
