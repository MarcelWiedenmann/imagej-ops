
package experimental.compgraph.channel.collection;

import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import net.imglib2.util.Pair;

import experimental.compgraph.channel.OpsBoundedChannel;

public interface OpsList<I> extends OpsCollection<I> {

	// -- OpsCollection --

	@Override
	<I2> OpsList<Pair<I, I2>> join(OpsBoundedChannel<I2> c, BiPredicate<? super I, ? super I2> f);

	@Override
	<I2> OpsList<Pair<I, I2>> cartesian(OpsBoundedChannel<I2> c);

	@Override
	<O> OpsList<O> map(Function<? super I, O> f);

	@Override
	<O> OpsList<O> map(BiConsumer<? super I, ? extends Consumer<O>> f);

	@Override
	OpsList<I> filter(Predicate<? super I> f);

	@Override
	<O> OpsList<? extends OpsBoundedChannel<O>> partition(Function<? super I, O> f);
}
