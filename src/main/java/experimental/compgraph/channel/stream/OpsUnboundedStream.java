
package experimental.compgraph.channel.stream;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import experimental.compgraph.channel.OpsBoundedChannel;
import experimental.compgraph.channel.OpsChannel;
import experimental.compgraph.channel.collection.OpsCollection;
import experimental.compgraph.channel.collection.OpsElement;

public interface OpsUnboundedStream<I> extends OpsChannel<I> {

	<O> OpsElement<O> reduce(O memo, BiFunction<O, ? super I, O> f, BiFunction<O, O, O> merge, int window);

	// -- OpsChannel --

	@Override
	<O> OpsUnboundedStream<O> map(Function<? super I, O> f);

	@Override
	<O> OpsUnboundedStream<O> map(BiConsumer<? super I, ? extends Consumer<O>> f);

	@Override
	OpsUnboundedStream<I> filter(Predicate<? super I> f);

	@Override
	<O> OpsUnboundedStream<? extends OpsBoundedChannel<O>> partition(BiConsumer<? super I, ? extends Consumer<O>> f);

	@Override
	<O> OpsCollection<? extends OpsChannel<O>> group(Function<? super I, Integer> f); // TODO <--
}
