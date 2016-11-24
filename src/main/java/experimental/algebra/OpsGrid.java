
package experimental.algebra;

import java.util.function.Function;
import java.util.function.Predicate;

import net.imglib2.Interval;
import net.imglib2.util.Pair;

import experimental.algebra.rai.OpsRegion;

// TODO we may want to re-add OpsGrid to separate it from imglib2.
public interface OpsGrid<T> extends OpsCollection<T> {

	// TODO we need to take care that we go for OpsTiles -> OpsTiles whenever
	// possible.
	@Override
	<O> OpsGrid<O> map(Function<? super T, O> f);

	@Override
	OpsRegion<T> filter(Predicate<? super T> f);

	/* Special methods on grid */
	OpsCollection<Pair<T, T>> pairs(/*
									 * something which helps us iterating over
									 * pairs of a point
									 */);

	// filters a grid such that only grid coordinates within the interval are
	// left
	OpsGrid<T> filter(Interval val);

	// In theory we could simply call join internally... however, this would
	// suck in performance in the case we know we are joining a grid why
	// shouldn't we make use of this information (instanceof)
	// TODO we should add a join strategy, if g2 has fewer indices than g1 and
	// vice-versa (take only minimum, fail etc).
	// TODO for tilings we can then add OutOfBoundsStrategy! :-)
	<T2> OpsGrid<Pair<T, T2>> indexJoin(OpsGrid<T2> g2);

}
