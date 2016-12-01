
package experimental.compgraph.channel.collection;

import java.util.Comparator;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import net.imglib2.util.Pair;

import experimental.compgraph.channel.OpsChannel;
import experimental.compgraph.channel.stream.OpsBoundedStream;

public interface OpsCollection<I> extends OpsChannel<I>, Iterable<I> {

	/*
	 * -- Overrides --
	 */
	@Override
	<O> OpsCollection<O> map(Function<? super I, O> f);

	@Override
	<O> OpsCollection<O> map(BiConsumer<? super I, Consumer<O>> f);

	@Override
	OpsCollection<I> filter(Predicate<? super I> f);

	/*
	 * -- OpsCollection specific --
	 */

	<O> OpsElement<O> reduce(O memo, BiFunction<O, ? super I, O> f, BiFunction<O, O, O> merge);

	<O> OpsElement<O> treeReduce(BiFunction<O, O, O> aggregate);

	OpsCollection<I> concat(OpsCollection<I> c);

	<I2> OpsCollection<? extends Pair<I, I2>> join(OpsCollection<I2> c, BiPredicate<I, I2> f);

	<I2> OpsCollection<? extends Pair<I, I2>> cartesian(OpsCollection<I2> c);

	<O, C extends OpsCollection<O>> OpsCollection<C> partition(final Function<? super I, C> f);

	<O, C extends OpsCollection<O>> OpsCollection<C> group(final Function<? super I, Integer> f);

	/*
	 * -- is this part of the algebra (?) --
	 */
	OpsList<I> fixOrder();

	OpsList<I> sort(Comparator<I> f);

	OpsBoundedStream<I> stream();
}
