package experimental.algebra.rai;

import java.util.function.Function;

import experimental.algebra.OpsCollection;
import experimental.algebra.DOpsGrid;

public interface DOpsRAI<I> extends DOpsGrid<I> {

	@Override
	<O> DOpsRAI<O> map(Function<OpsCollection<I>, OpsCollection<O>> func);

	@Override
	OpsRAI<I> merge();

}
