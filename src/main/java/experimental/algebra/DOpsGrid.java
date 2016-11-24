package experimental.algebra;

import java.util.function.Function;

public interface DOpsGrid<I> extends DOpsCollection<I> {

	@Override
	<O> DOpsGrid<O> map(Function<OpsCollection<I>, OpsCollection<O>> func);

	@Override
	OpsGrid<I> merge();

}
