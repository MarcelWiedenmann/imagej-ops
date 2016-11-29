
package experimental.compgraph.channel.collection;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import net.imglib2.converter.Converter;
import net.imglib2.util.Pair;

public interface OpsRai<I> extends OpsGrid<I> {

	@Override
	<O> OpsRai<O> map(Function<? super I, O> f);

	@Override
	<O> OpsRai<O> map(BiConsumer<? super I, Consumer<O>> f);

	@Override
	<I2, C extends OpsGrid<I2>> OpsRai<Pair<I, I2>> join(final C g);

	@Override
	OpsIterableInterval<I> filter(Predicate<? super I> f);

	<O> OpsRai<O> mapPixel(Converter<I, O> c);

}
