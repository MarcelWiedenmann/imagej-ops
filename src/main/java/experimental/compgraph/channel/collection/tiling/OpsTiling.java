
package experimental.compgraph.channel.collection.tiling;

import java.util.function.Function;

import net.imglib2.RandomAccessibleInterval;
import net.imglib2.util.Pair;

import experimental.compgraph.channel.collection.OpsCollection;
import experimental.compgraph.channel.collection.OpsGrid;
import experimental.compgraph.channel.collection.nested.OpsNestedGrid;

public interface OpsTiling<T> extends OpsNestedGrid<T, OpsTile<T>> {

	<O> OpsTiling<O> mapTile(Function<? super OpsTile<T>, RandomAccessibleInterval<O>> f);

	// -- Overrides --

	@Override
	<I2> OpsNestedGrid<Pair<T, I2>, OpsGrid<Pair<T, I2>>> cartesianEach(final OpsCollection<I2> c);
}
