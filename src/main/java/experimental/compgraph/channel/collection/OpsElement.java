
package experimental.compgraph.channel.collection;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import experimental.compgraph.channel.OpsBoundedChannel;

public interface OpsElement<I> extends OpsBoundedChannel<I> {

	// -- OpsChannel --

	@Override
	<O> OpsElement<O> map(final Function<? super I, O> f);

	@Override
	<O> OpsElement<O> map(final BiConsumer<? super I, ? extends Consumer<O>> f);

	@Override
	OpsElement<I> filter(final Predicate<? super I> f);

	@Override
	<O> OpsElement<? extends OpsBoundedChannel<O>> partition(final Function<? super I, O> f);

	@Override
	<O> OpsBoundedChannel<? extends OpsBoundedChannel<O>> group(final Function<? super I, Integer> f);
}
