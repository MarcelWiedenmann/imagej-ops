
package experimental.algebra.rai;

import java.util.function.Function;

import experimental.algebra.OpsCollection;

// tile is the thing which provides information about location which can be reused for composition again.
public interface OpsTiling<T> extends OpsRAINested<OpsTile<T>> {

	@Override
	<O> OpsRAINested<O> map(Function<OpsCollection<OpsTile<T>>, OpsCollection<O>> func);
	
	public static interface TilingHints {
		//
		// private Interval tileSize;
		//
		// public TilingHints(final long[] tileSize) {
		// this.tileSize = new FinalInterval(tileSize);
		// }

		long[] getTilingHints();
	}

}
