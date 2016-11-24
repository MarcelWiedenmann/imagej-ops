
package experimental.algebra;

import java.util.function.Function;
import java.util.function.Predicate;

import net.imglib2.Interval;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.algorithm.neighborhood.Shape;
import net.imglib2.util.Pair;

import experimental.algebra.rai.OpsRegion;
import experimental.algebra.rai.OpsTiling;
import experimental.algebra.rai.TilingHints;

// TODO we may want to re-add OpsGrid to separate it from imglib2.
public interface OpsRAI<T> extends OpsCollection<T>, RandomAccessibleInterval<T> {

	// TODO we need to take care that we go for OpsTiles -> OpsTiles whenever
	// possible.
	@Override
	<O> OpsRAI<O> map(Function<? super T, O> f);

	@Override
	OpsRegion<T> filter(Predicate<? super T> f);

	@Override
	<O, C extends OpsCollection<O>> OpsRAI<C> partition(final Function<T, C> f);

	@Override
	OpsRAINested<T> scatter(Function<T, Integer> func);

	/* Special methods on grid */
	OpsCollection<Pair<T, T>> pairs(/*
									 * something which helps us iterating over
									 * pairs of a point
									 */);

	/* alle neighbors */
	OpsRAI<OpsCollection<T>> neighbors(final Shape s);

	// filters a grid such that only grid coordinates within the interval are
	// left
	OpsRAI<T> filter(Interval val);

	// In theory we could simply call join internally... however, this would
	// suck in performance in the case we know we are joining a grid why
	// shouldn't we make use of this information (instanceof)
	// TODO we should add a join strategy, if g2 has fewer indices than g1 and
	// vice-versa (take only minimum, fail etc).
	// TODO for tilings we can then add OutOfBoundsStrategy! :-)
	<T2> OpsRAI<Pair<T, T2>> indexJoin(OpsRAI<T2> g2);

	// TODO basically a map followed by a scatter
	OpsTiling<T> partition(TilingHints hints);
}
