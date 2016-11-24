
package experimental.algebra.rai;

import java.util.function.Function;

// tile is the thing which provides information about location which can be reused for composition again.
public interface DOpsTiling<T> extends DOpsRAI<OpsTile<T>> {

	@Override
	OpsRAI<OpsTile<T>> merge();

	/**
	 * Special for tilings
	 */
	OpsRAI<T> mergeTiles();

	// TODO
	default <O> DOpsTiling<O> mapTiles(Function<? super OpsTile<T>, OpsTile<O>> func) {
		map((c) -> c.map(func));
		// convert to OpsTiling again
		return null;
	}
}
