package experimental.algebra.rai;

import java.util.function.Function;

import net.imglib2.RandomAccessibleInterval;
import net.imglib2.algorithm.neighborhood.Shape;

import experimental.algebra.DOpsGrid;
import experimental.algebra.OpsCollection;
import experimental.algebra.OpsGrid;

public interface DOpsRAI<I> extends DOpsGrid<I>, RandomAccessibleInterval<I> {

	@Override
	<O> DOpsRAI<O> map(Function<OpsCollection<I>, OpsCollection<O>> func);

	@Override
	OpsRAI<I> merge();

	/* alle neighbors */
	OpsGrid<OpsCollection<I>> neighbors(final Shape s);

}
