
package experimental.compgraph.channel.collection;

import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import net.imglib2.Interval;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.algorithm.neighborhood.Shape;
import net.imglib2.util.Pair;

import experimental.compgraph.channel.OpsBoundedChannel;
import experimental.compgraph.channel.collection.img.OpsIterableInterval;

public interface OpsGrid<I> extends OpsOrderedCollection<I>, RandomAccessibleInterval<I> {

	OpsGrid<I> interval(Interval i);

	OpsGrid<I> subsample(long... steps);

	OpsGrid<? extends OpsIterableInterval<I>> neighbors(Shape s);

	// -- OpsList --

	@Override
	<I2> OpsBoundedChannel<? extends Pair<I, I2>> join(OpsBoundedChannel<I2> c, BiPredicate<? super I, ? super I2> f);

	@Override
	<I2> OpsBoundedChannel<? extends Pair<I, I2>> cartesian(OpsBoundedChannel<I2> c);

	@Override
	<O> OpsGrid<O> map(Function<? super I, O> f);

	@Override
	<O> OpsGrid<O> map(BiConsumer<? super I, ? extends Consumer<O>> f);

	// TODO: What means filtering a grid?
	// If we want to preserve our grid structure, we'll have to define an 'empty cell value'.
	@Override
	OpsIterableInterval<I> filter(Predicate<? super I> f);

	@Override
	<O> OpsGrid<? extends OpsBoundedChannel<O>> partition(BiConsumer<? super I, ? extends Consumer<O>> f);

	// -- OpsCollection --

	@Override
	OpsGrid<I> fixOrder();
}
