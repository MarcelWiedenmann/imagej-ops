
package experimental.compgraph.channel.collection;

import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import net.imglib2.util.Pair;

import experimental.compgraph.channel.OpsBoundedChannel;

public interface OpsOrderedCollection<I> extends OpsCollection<I> {

	<I2> OpsOrderedCollection<? extends Pair<I, I2>> join(OpsOrderedCollection<I2> c);

	// -- OpsCollection --

	@Override
	<I2> OpsBoundedChannel<? extends Pair<I, I2>> join(OpsBoundedChannel<I2> c, BiPredicate<? super I, ? super I2> f);

	@Override
	<I2> OpsBoundedChannel<? extends Pair<I, I2>> cartesian(OpsBoundedChannel<I2> c);

	@Override
	<O> OpsOrderedCollection<O> map(Function<? super I, O> f);

	@Override
	<O> OpsOrderedCollection<O> map(BiConsumer<? super I, ? extends Consumer<O>> f);

	@Override
	OpsOrderedCollection<I> filter(Predicate<? super I> f);

	@Override
	<O> OpsOrderedCollection<? extends OpsBoundedChannel<O>> partition(BiConsumer<? super I, ? extends Consumer<O>> f);
}
