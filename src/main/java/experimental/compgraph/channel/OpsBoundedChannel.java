
package experimental.compgraph.channel;

import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import net.imglib2.util.Pair;

public interface OpsBoundedChannel<I> extends OpsChannel<I> {

	<I2> OpsBoundedChannel<Pair<I, I2>> join(OpsBoundedChannel<I2> c, BiPredicate<? super I, ? super I2> f);

	<I2> OpsBoundedChannel<Pair<I, I2>> cartesian(OpsBoundedChannel<I2> c);

	// -- OpsChannel --

	@Override
	<O> OpsBoundedChannel<O> map(Function<? super I, O> f);

	@Override
	<O> OpsBoundedChannel<O> map(BiConsumer<? super I, ? extends Consumer<O>> f);

	@Override
	OpsBoundedChannel<I> filter(Predicate<? super I> f);

	@Override
	<O> OpsBoundedChannel<? extends OpsBoundedChannel<O>> partition(BiConsumer<? super I, ? extends Consumer<O>> f);

	@Override
	<O> OpsBoundedChannel<? extends OpsBoundedChannel<O>> group(Function<? super I, Integer> f);
}
