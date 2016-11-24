
package experimental.algebra.rai;

import java.util.function.Function;

import experimental.algebra.OpsRAI;

// tile is the thing which provides information about location which can be reused for composition again.
public interface OpsTiling<T> extends OpsRAI<OpsTile<T>> {

	@Override
	default <O> OpsRAI<O> map(Function<? super OpsTile<T>, O> f) {
		// TODO Auto-generated method stub
		return null;
	}
}
