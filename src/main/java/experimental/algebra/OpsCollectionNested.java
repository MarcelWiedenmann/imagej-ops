package experimental.algebra;

import java.util.function.Function;

public interface OpsCollectionNested<I> {

	<O> OpsCollectionNested<O> map(final Function<OpsCollection<I>, OpsCollection<O>> func);

	default <O> OpsCollectionNested<O> forEach(final Function<? super I, O> func) {
		return map((c) -> c.map(func));
	}

	OpsCollection<I> merge();
}
