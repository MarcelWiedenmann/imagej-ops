package experimental.algebra;

import java.util.function.Function;

public interface OpsGridNested<I, C extends OpsCollection<I>> extends OpsCollectionNested<I, C>, OpsGrid<C> {

	@Override
	<O, CI extends OpsGrid<I>, CC extends OpsCollection<O>> OpsGridNested<O, CC> collMap(
			final Function<CI, CC> func);
	
	@Override
	OpsGrid<I> merge();
}
