
package experimental.algebra.rai;

import java.util.function.Function;

import experimental.algebra.OpsCollection;
import experimental.algebra.OpsGrid;

public interface OpsTiling<T> extends OpsRAI<OpsTile<T>> {

	@Override
	default <O> OpsGrid<O> map(Function<? super OpsTile<T>, O> f) {
		// TODO Auto-generated method stub
		return null;
	}
}
