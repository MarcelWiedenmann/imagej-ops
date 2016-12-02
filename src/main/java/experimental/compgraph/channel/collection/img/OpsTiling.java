
package experimental.compgraph.channel.collection.img;

import java.util.function.Function;
import java.util.function.Predicate;

import net.imglib2.Interval;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.converter.Converter;

import experimental.compgraph.channel.collection.OpsGrid;

public interface OpsTiling<I> extends OpsGrid<OpsTile<I>> {

	// NB: To get direct pixel level access.
	OpsRai<I> toRai();

	<O> OpsRai<O> toRai(Function<? super OpsTiling<I>, RandomAccessibleInterval<O>> f);

	// NB: Use map(..) and transform(..).
	<O> OpsTiling<O> toTiling(Converter<I, O> c);

	<O> OpsTiling<O> mapTile(Function<? super OpsTile<I>, RandomAccessibleInterval<O>> f);

	// -- OpsGrid --

	@Override
	OpsTiling<I> interval(Interval i);

	@Override
	OpsTiling<I> subsample(long... steps);

	@Override
	OpsTiling<I> filter(Predicate<? super OpsTile<I>> f);

	@Override
	OpsTiling<I> fixOrder();
}
