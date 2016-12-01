
package experimental.compgraph.channel.collection.rai;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import net.imglib2.converter.Converter;
import net.imglib2.util.Pair;

import experimental.compgraph.channel.collection.OpsGrid;
import experimental.compgraph.channel.collection.OpsIterableInterval;
import experimental.compgraph.channel.collection.tiling.OpsTiling;

public interface OpsRai<I> extends OpsGrid<I> {

	OpsTiling<I> tile(long[] tilesPerDim, long[] overlap);

	<O> OpsRai<O> mapPixel(Converter<I, O> c);

	// -- Overrides --

	@Override
	<O> OpsRai<O> map(Function<? super I, O> f);

	@Override
	<O> OpsRai<O> map(BiConsumer<? super I, Consumer<O>> f);

	@Override
	<I2, C extends OpsGrid<I2>> OpsRai<Pair<I, I2>> join(final C g);

	@Override
	OpsIterableInterval<I> filter(Predicate<? super I> f);
}
