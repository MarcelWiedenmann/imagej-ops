
package experimental.compgraph.channel.collection;

import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import net.imglib2.util.Pair;

import experimental.compgraph.channel.OpsBoundedChannel;

public interface OpsElement<I> extends OpsBoundedChannel<I> {

	// -- OpsBoundedChannel --

	@Override
	<I2> OpsElement<? extends Pair<I, I2>> join(OpsBoundedChannel<I2> c, BiPredicate<? super I, ? super I2> f);

	@Override
	<I2> OpsElement<? extends Pair<I, I2>> cartesian(OpsBoundedChannel<I2> c);

	@Override
	<O> OpsElement<O> map(final Function<? super I, O> f);

	@Override
	<O> OpsElement<O> map(final BiConsumer<? super I, ? extends Consumer<O>> f);

	@Override
	OpsElement<I> filter(final Predicate<? super I> f);

	@Override
	<O> OpsElement<? extends OpsBoundedChannel<O>> partition(BiConsumer<? super I, ? extends Consumer<O>> f);

	@Override
	<O> OpsBoundedChannel<? extends OpsBoundedChannel<O>> group(final Function<? super I, Integer> f);
}
