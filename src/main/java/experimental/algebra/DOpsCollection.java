package experimental.algebra;

import java.util.function.Function;

public interface DOpsCollection<I> {

	<O> DOpsCollection<O> map(final Function<OpsCollection<I>, OpsCollection<O>> func);

	default <O> DOpsCollection<O> forEach(final Function<? super I, O> func) {
		return map((c) -> c.map(func));
	}

	OpsCollection<I> merge();
}
