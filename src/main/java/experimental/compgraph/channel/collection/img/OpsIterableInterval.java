
package experimental.compgraph.channel.collection.img;

import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import net.imglib2.IterableInterval;
import net.imglib2.util.Pair;

import experimental.compgraph.channel.OpsBoundedChannel;
import experimental.compgraph.channel.collection.OpsOrderedCollection;

public interface OpsIterableInterval<I> extends OpsOrderedCollection<I>, IterableInterval<I> {

	// NB: Marker interface to be compatible to several Ops implementations.

	// -- OpsCollection --

	@Override
	<I2> OpsIterableInterval<Pair<I, I2>> join(OpsBoundedChannel<I2> c, BiPredicate<? super I, ? super I2> f);

	@Override
	<I2> OpsIterableInterval<Pair<I, I2>> cartesian(OpsBoundedChannel<I2> c);

	@Override
	<O> OpsIterableInterval<O> map(Function<? super I, O> f);

	@Override
	<O> OpsIterableInterval<O> map(BiConsumer<? super I, ? extends Consumer<O>> f);

	@Override
	OpsIterableInterval<I> filter(Predicate<? super I> f);

	@Override
	<O> OpsIterableInterval<? extends OpsBoundedChannel<O>> partition(BiConsumer<? super I, ? extends Consumer<O>> f);
}
