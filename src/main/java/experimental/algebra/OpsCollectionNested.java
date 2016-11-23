package experimental.algebra;

import java.util.function.Function;

public interface OpsCollectionNested<I, C extends OpsCollection<I>> extends OpsCollection<C> {

	<O, CI extends OpsCollection<I>, CC extends OpsCollection<O>> OpsCollectionNested<O, CC> collMap(
			final Function<CI, CC> func);

	OpsCollection<I> merge();
}
