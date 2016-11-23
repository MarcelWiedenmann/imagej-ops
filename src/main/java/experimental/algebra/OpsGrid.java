
package experimental.algebra;

import java.util.function.Function;
import java.util.function.Predicate;

import net.imglib2.Interval;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.util.Pair;

import experimental.algebra.img.OpsRegion;
import experimental.algebra.img.OpsTile;
import experimental.algebra.img.OpsTiling;
import experimental.algebra.img.TilingHints;

public interface OpsGrid<T> extends OpsCollection<T>, RandomAccessibleInterval<T> {

	OpsGrid<T> filter(Interval val);

	// In theory we could simply call join internally... however, this would
	// suck in performance in the case we know we are joining a grid why
	// shouldn't we make use of this information (instanceof)
	
	// TODO we should add a join strategy, if g2 has fewer indices than g1 and vice-versa (take only minimum, fail etc).  
	// TODO for tilings we can then add OutOfBoundsStrategy! :-)
	<T2> OpsGrid<Pair<T, T2>> indexJoin(OpsGrid<T2> g2);

	@Override
	<O> OpsGrid<O> map(Function<? super T, O> f);

	@Override
	default OpsRegion<T> filter(Predicate<T> f) {
		return null;
	}

	// Internally (or default) we can call the standard partition function.
	OpsTiling<T, OpsTile<T>> partition(final TilingHints hints);

}
