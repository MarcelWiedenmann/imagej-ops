package experimental.algebra;

import java.util.function.Function;

public interface OpsCollectionNested<I, C extends OpsCollection<I>> extends OpsCollection<C> {

	<O, CC extends OpsCollection<O>> OpsCollectionNested<O, CC> collMap(
			final Function<C, CC> func);

	OpsCollection<I> merge();
}
