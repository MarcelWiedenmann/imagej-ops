
package experimental.algebra;

import java.util.Comparator;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import net.imglib2.util.Pair;

public interface OpsCollection<I> extends OpsChannel<I> {

	/*
	 * Overrides
	 */
	@Override
	<O> OpsCollection<O> map(Function<? super I, O> f);

	@Override
	<O> OpsCollection<O> map(BiConsumer<? super I, Consumer<O>> f);

	@Override
	OpsCollection<I> filter(Predicate<? super I> f);

	/*
	 * OpsCollection specific
	 */
	<O> OpsCollection<O> reduce(O memo, BiFunction<O, ? super I, O> f);

	<O, K> OpsCollection<O> aggregate(BiFunction<O, ? super I, O> f);

	OpsCollection<I> concat(OpsCollection<I> c);

	<I2> OpsCollection<Pair<I, I2>> join(OpsCollection<I2> c, BiPredicate<I, I2> f);

	<I2> OpsCollection<Pair<I, I2>> cartesian(OpsCollection<I2> c);

	OpsList<I> sort(Comparator<I> f);

	OpsBoundedStream<I> stream();

	// -- Higher Order Operator --
	<O, C extends OpsCollection<O>> OpsCollection<C> partition(final Function<? super I, C> func);

	<O, C extends OpsCollection<O>> OpsCollection<C> group(final Function<? super I, Integer> func);
}
