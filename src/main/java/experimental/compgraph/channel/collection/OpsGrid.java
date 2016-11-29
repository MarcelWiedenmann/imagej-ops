
package experimental.compgraph.channel.collection;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import net.imglib2.RandomAccessibleInterval;
import net.imglib2.util.Pair;

public interface OpsGrid<I> extends OpsCollection<I>, RandomAccessibleInterval<I> {

	@Override
	<O> OpsGrid<O> map(Function<? super I, O> f);

	@Override
	<O> OpsGrid<O> map(BiConsumer<? super I, Consumer<O>> f);

	// joins two grids by index
	<I2, C extends OpsGrid<I2>> OpsGrid<Pair<I, I2>> join(final C g);

}
