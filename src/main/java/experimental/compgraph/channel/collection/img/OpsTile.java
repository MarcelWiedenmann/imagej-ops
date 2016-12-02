
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

// tile only represents inner part of cropped image (but see todo below)
public interface OpsTile<T> extends OpsRai<T>, Localizable {

	long[] getOverlap();

	// gets the entire tile
	// TODO: or change to getInnerInterval() and interpret OpsTile<T> as the entire tile?
	// (use case: chaining of multiple filters (= convolutions), entire interval (incl. overlap) needs to be processed)
	OpsRai<T> getOverlapInterval();

	// -- OpsRai --

	@Override
	<O> OpsTile<O> toRai(Function<? super OpsRai<T>, RandomAccessibleInterval<O>> f);

	@Override
	<O> OpsTile<O> toRai(Converter<T, O> c);

	@Override
	OpsTile<T> interval(Interval i);

	@Override
	OpsTile<T> subsample(long... steps);

	@Override
	<I2> OpsTile<Pair<T, I2>> join(OpsBoundedChannel<I2> c, BiPredicate<? super T, ? super I2> f);

	@Override
	<I2> OpsTile<Pair<T, I2>> cartesian(OpsBoundedChannel<I2> c);

	@Override
	<O> OpsTile<O> map(Function<? super T, O> f);

	@Override
	<O> OpsTile<O> map(BiConsumer<? super T, ? extends Consumer<O>> f);

	@Override
	OpsTile<T> filter(Predicate<? super T> f);

	@Override
	<O> OpsTile<? extends OpsBoundedChannel<O>> partition(Function<? super T, O> f);

	@Override
	OpsTile<T> fixOrder();
}
