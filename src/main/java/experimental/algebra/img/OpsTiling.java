
package experimental.algebra.img;

import java.util.function.Function;

import experimental.algebra.OpsGrid;

// tile is the thing which provides information about location which can be reused for composition again.
public interface OpsTiling<T> extends OpsGrid<OpsTile<T>> {

	@Override
	default <O> OpsGrid<O> map(Function<? super OpsTile<T>, O> f) {
		// TODO Auto-generated method stub
		return null;
	}
}
