
package experimental.compgraph.channel.stream;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import net.imglib2.util.Pair;

import experimental.compgraph.channel.OpsBoundedChannel;
import experimental.compgraph.channel.collection.OpsCollection;
import experimental.compgraph.channel.collection.OpsElement;
import experimental.compgraph.channel.collection.OpsOrderedCollection;

public interface OpsBoundedStream<I> extends OpsUnboundedStream<I>, OpsBoundedChannel<I> {

	OpsOrderedCollection<I> collect();

	// -- OpsUnboundedStream --

	@Override
	<O> OpsElement<O> reduce(O memo, BiFunction<O, ? super I, O> f, BinaryOperator<O> merge, int window);

	@Override
	<O> OpsBoundedStream<O> map(Function<? super I, O> f);

	@Override
	<O> OpsBoundedStream<O> map(BiConsumer<? super I, ? extends Consumer<O>> f);

	@Override
	OpsBoundedStream<I> filter(Predicate<? super I> f);

	@Override
	<O> OpsBoundedStream<? extends OpsBoundedChannel<O>> partition(BiConsumer<? super I, ? extends Consumer<O>> f);

	@Override
	<O> OpsCollection<? extends OpsBoundedChannel<O>> group(Function<? super I, Integer> f);

	// -- OpsBoundedChannel --

	@Override
	<I2> OpsBoundedChannel<? extends Pair<I, I2>> join(OpsBoundedChannel<I2> c, BiPredicate<? super I, ? super I2> f);

	@Override
	<I2> OpsBoundedChannel<? extends Pair<I, I2>> cartesian(OpsBoundedChannel<I2> c);
}
