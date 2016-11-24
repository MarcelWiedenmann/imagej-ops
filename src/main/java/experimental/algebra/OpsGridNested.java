package experimental.algebra;

import java.util.function.Function;

public interface OpsGridNested<I> extends OpsCollectionNested<I> {

	@Override
	<O> OpsGridNested<O> map(Function<OpsCollection<I>, OpsCollection<O>> func);

	@Override
	OpsGrid<I> merge();

}
