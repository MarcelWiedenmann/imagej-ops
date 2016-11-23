package experimental.algebra;

import java.util.function.Function;

public interface OpsGridNested<I, C extends OpsCollection<I>> extends OpsCollectionNested<I, C>, OpsGrid<C> {

	@Override
	<O, CC extends OpsCollection<O>> OpsGridNested<O, CC> collMap(final Function<C, CC> func);

	@Override
	OpsGrid<I> merge();
}
