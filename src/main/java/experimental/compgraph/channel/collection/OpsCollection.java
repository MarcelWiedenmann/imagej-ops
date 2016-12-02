
package experimental.compgraph.channel.collection;

import java.util.Comparator;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import net.imglib2.util.Pair;

import experimental.compgraph.channel.OpsBoundedChannel;
import experimental.compgraph.channel.stream.OpsBoundedStream;

public interface OpsCollection<I> extends OpsBoundedChannel<I>, Iterable<I> {

	OpsCollection<I> concat(OpsCollection<I> c);

	<O> OpsElement<O> reduce(O memo, BiFunction<O, ? super I, O> f, BiFunction<O, O, O> merge);

	<O> OpsElement<O> treeReduce(BiFunction<O, O, O> f);

	OpsList<I> fixOrder();

	OpsList<I> sort(Comparator<I> f);

	// NB: Use transform(..) internally.
	OpsBoundedStream<I> stream();

	// -- OpsBoundedChannel --

	@Override
	<I2> OpsCollection<Pair<I, I2>> join(OpsBoundedChannel<I2> c, BiPredicate<? super I, ? super I2> f);

	@Override
	<I2> OpsCollection<Pair<I, I2>> cartesian(OpsBoundedChannel<I2> c);

	// -- OpsChannel --

	@Override
	<O> OpsCollection<O> map(Function<? super I, O> f);

	@Override
	<O> OpsCollection<O> map(BiConsumer<? super I, ? extends Consumer<O>> f);

	@Override
	OpsCollection<I> filter(Predicate<? super I> f);

	@Override
	<O> OpsCollection<? extends OpsBoundedChannel<O>> partition(Function<? super I, O> f);

	@Override
	<O> OpsCollection<? extends OpsBoundedChannel<O>> group(Function<? super I, Integer> f);

	// TODO: override Iterable methods?
}
