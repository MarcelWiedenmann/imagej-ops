package experimental.algebra.rai;

import java.util.function.Function;

import experimental.algebra.OpsCollection;
import experimental.algebra.OpsGridNested;

public interface OpsRAINested<I> extends OpsGridNested<I> {

	@Override
	<O> OpsRAINested<O> map(Function<OpsCollection<I>, OpsCollection<O>> func);

	@Override
	OpsRAI<I> merge();

}
