package experimental.algebra.rai;

import java.util.function.Function;

import net.imglib2.RandomAccessibleInterval;

import experimental.algebra.OpsGrid;

// pure syntactical sugar
public interface NestedOpsRAI<T> extends OpsGrid<OpsRAI<T>> {
	<O> NestedOpsRAI<O> mapRAI(Function<? super OpsRAI<T>, RandomAccessibleInterval<O>> f);

	// TODO can internally call some reduce stuff etc
	OpsRAI<T> flatten();
}
