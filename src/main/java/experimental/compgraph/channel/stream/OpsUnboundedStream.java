
package experimental.compgraph.channel.stream;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import experimental.compgraph.channel.OpsChannel;
import experimental.compgraph.channel.collection.OpsCollection;

public interface OpsUnboundedStream<I> extends OpsChannel<I> {

	@Override
	OpsUnboundedStream<I> filter(Predicate<? super I> f);

	@Override
	public <O> OpsUnboundedStream<O> map(Function<? super I, O> func);

	@Override
	public <O> OpsCollection<O> map(BiConsumer<? super I, Consumer<O>> f);

	<O> OpsUnboundedStream<O> reduce(BiFunction<O, I, O> f, int window);
}
