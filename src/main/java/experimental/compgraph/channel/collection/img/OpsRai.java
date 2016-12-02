
package experimental.compgraph.channel.collection.img;

import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import net.imglib2.Interval;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.converter.Converter;
import net.imglib2.util.Pair;

import experimental.compgraph.channel.OpsBoundedChannel;
import experimental.compgraph.channel.collection.OpsGrid;

public interface OpsRai<I> extends OpsGrid<I> {

	// TODO: Further domain specific methods such as 'slice, stack, ...' (here or in OpsGrid)?

	// NB: Use transform(..).
	<O> OpsRai<O> toRai(Function<? super OpsRai<I>, RandomAccessibleInterval<O>> f);

	// NB: Use map(..) and transform(..) internally.
	<O> OpsRai<O> toRai(Converter<I, O> c);

	// NB: Use transform(..) or partition(..) internally.
	OpsTiling<I> toTiling(long[] tilesPerDim, long[] overlap);

	// -- OpsGrid --

	@Override
	OpsRai<I> interval(Interval i);

	@Override
	OpsRai<I> subsample(long... steps);

	@Override
	<I2> OpsRai<Pair<I, I2>> join(OpsBoundedChannel<I2> c, BiPredicate<? super I, ? super I2> f);

	@Override
	<I2> OpsRai<Pair<I, I2>> cartesian(OpsBoundedChannel<I2> c);

	@Override
	<O> OpsRai<O> map(Function<? super I, O> f);

	@Override
	<O> OpsRai<O> map(BiConsumer<? super I, ? extends Consumer<O>> f);

	@Override
	OpsRai<I> filter(Predicate<? super I> f);

	@Override
	<O> OpsRai<? extends OpsBoundedChannel<O>> partition(Function<? super I, O> f);

	@Override
	OpsRai<I> fixOrder();
}
