
package experimental.compgraph.channel.collection.img;

import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import net.imglib2.Interval;
import net.imglib2.Localizable;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.converter.Converter;
import net.imglib2.util.Pair;

import experimental.compgraph.channel.OpsBoundedChannel;

// NB: Tile only represents inner part of cropped image (but see TODO below).
public interface OpsTile<I> extends OpsRai<I>, Localizable {

	long[] getOverlap();

	// gets the entire tile
	// TODO: or change to getInnerInterval() and interpret OpsTile<T> as the entire tile?
	// (use case: chaining of multiple filters (= convolutions), entire interval (incl. overlap) needs to be processed)
	OpsRai<I> getOverlapInterval();

	// -- OpsRai --

	@Override
	<O> OpsTile<O> toRai(Function<? super OpsRai<I>, ? extends RandomAccessibleInterval<O>> f);

	@Override
	<O> OpsTile<O> toRai(Converter<? super I, O> c);

	@Override
	OpsTile<I> interval(Interval i);

	@Override
	OpsTile<I> subsample(long... steps);

	@Override
	<I2> OpsBoundedChannel<? extends Pair<I, I2>> join(OpsBoundedChannel<I2> c, BiPredicate<? super I, ? super I2> f);

	@Override
	<I2> OpsBoundedChannel<? extends Pair<I, I2>> cartesian(OpsBoundedChannel<I2> c);

	@Override
	<O> OpsTile<O> map(Function<? super I, O> f);

	@Override
	<O> OpsTile<O> map(BiConsumer<? super I, ? extends Consumer<O>> f);

	@Override
	OpsIterableInterval<I> filter(Predicate<? super I> f);

	@Override
	<O> OpsTile<? extends OpsBoundedChannel<O>> partition(BiConsumer<? super I, ? extends Consumer<O>> f);

	@Override
	OpsTile<I> fixOrder();
}
