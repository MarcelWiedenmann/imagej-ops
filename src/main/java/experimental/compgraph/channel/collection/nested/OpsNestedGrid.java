
package experimental.compgraph.channel.collection.nested;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import net.imglib2.Interval;
import net.imglib2.util.Pair;

import experimental.compgraph.channel.OpsChannel;
import experimental.compgraph.channel.collection.OpsCollection;
import experimental.compgraph.channel.collection.OpsGrid;

public interface OpsNestedGrid<I, C extends OpsChannel<I>> extends OpsNestedCollection<I, C>, OpsGrid<C> {

	// special methods on grids
	OpsNestedGrid<I, C> interval(Interval i);

	OpsNestedGrid<I, C> subsample(long... steps);

	<I2> OpsGrid<? extends OpsChannel<? extends Pair<I, I2>>> cartesianEach(final OpsCollection<I2> c);

	// -- Overrides --

	@Override
	OpsGrid<I> treeReduceEach(BiFunction<? super I, ? super I, I> f);

	@Override
	<O> OpsGrid<O> reduceEach(O memo, BiFunction<O, ? super I, O> f, BiFunction<? super O, ? super O, O> merge);

	@Override
	<O, CC extends OpsChannel<O>> OpsNestedGrid<O, CC> mapEach(BiConsumer<? super I, Consumer<O>> f);

	@Override
	<O, CC extends OpsChannel<O>> OpsNestedGrid<O, CC> mapEach(Function<? super I, O> f);
}
