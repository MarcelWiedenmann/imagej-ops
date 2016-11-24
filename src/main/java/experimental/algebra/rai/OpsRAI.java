package experimental.algebra.rai;

import java.util.function.Function;

import experimental.algebra.OpsCollection;
import experimental.algebra.OpsGridNested;
import experimental.algebra.rai.OpsTiling.TilingHints;

public interface OpsRAI<T> extends OpsGrid<T> {

	@Override
	<O> OpsRAI<O> map(Function<? super T, O> f);

	@Override
	<O, C extends OpsCollection<O>> OpsGridNested<O> partition(Function<T, C> f);
	
	OpsTiling<T> partition(TilingHints hints);
}
