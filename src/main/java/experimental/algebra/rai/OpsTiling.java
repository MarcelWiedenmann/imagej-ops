
package experimental.algebra.rai;

import java.util.function.Function;

import net.imglib2.RandomAccessibleInterval;

public interface OpsTiling<T> extends OpsRAI<OpsTile<T>> {

	// map tiles
	<O> OpsTiling<O> mapTiles(Function<? super OpsTile<T>, RandomAccessibleInterval<O>> func);

	// make it a OpsRAI<T> again.
	OpsRAI<T> flatten();

}
