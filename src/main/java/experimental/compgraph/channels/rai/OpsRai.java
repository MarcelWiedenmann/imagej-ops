
package experimental.compgraph.channels.rai;

import java.util.function.Predicate;

import net.imglib2.RandomAccessibleInterval;
import net.imglib2.converter.Converter;

import experimental.compgraph.channels.OpsGrid;

public interface OpsRai<T> extends OpsGrid<T>, RandomAccessibleInterval<T> {

	<O> OpsRai<O> mapPixel(Converter<T, O> c);

	@Override
	OpsIterableInterval<T> filter(Predicate<? super T> f);
}
