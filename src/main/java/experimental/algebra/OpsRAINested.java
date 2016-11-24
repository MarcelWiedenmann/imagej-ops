package experimental.algebra;

import java.util.function.Function;

public interface OpsRAINested<I> extends OpsCollectionNested<I> {

	@Override
	<O> OpsRAINested<O> map(Function<OpsCollection<I>, OpsCollection<O>> func);

	@Override
	OpsRAI<I> merge();

}
