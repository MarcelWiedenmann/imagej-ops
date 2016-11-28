
package experimental.algebra;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import experimental.compgraph.UnaryEdge;

public interface OpsUnboundedStream<I> extends OpsChannel<I>,
	UnaryEdge<I> /* or OpsChannel extends UnaryEdge or wrapper? (just need it somewhere :D) */
{

	@Override
	OpsUnboundedStream<I> filter(Predicate<? super I> f);

	@Override
	public <O> OpsUnboundedStream<O> map(Function<? super I, O> func);

	@Override
	public <O> OpsCollection<O> map(BiConsumer<? super I, Consumer<O>> f);

	<O> OpsUnboundedStream<O> reduce(BiFunction<O, I, O> f, int window);
}
