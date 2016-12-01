
package experimental.cache;

import java.util.function.Function;

import net.imglib2.RandomAccessibleInterval;
import net.imglib2.util.Pair;

import experimental.compgraph.channel.collection.OpsCollection;
import experimental.compgraph.channel.collection.OpsElement;
import experimental.compgraph.channel.collection.OpsGrid;
import experimental.compgraph.channel.rai.OpsRai;
import experimental.compgraph.channel.rai.OpsRaiCollection;
import experimental.compgraph.channel.rai.OpsRaiGrid;

public interface OpsTiling_old<T> extends OpsRaiGrid<T> {

	@Override
	<O> OpsGrid<O> map(Function<? super OpsRai<T>, O> f);

	<O> OpsTiling_old<O> tileMap(Function<? super OpsRai<T>, ? extends RandomAccessibleInterval<O>> f);

	@Override
	OpsGrid<OpsTiling_old<T>> tile(long[] tilesPerDimension, long[] overlap);

	@Override
	<I2> OpsCollection<Pair<OpsRai<T>, I2>> cartesian(final OpsCollection<I2> c);

	<I2> OpsGrid<Pair<OpsRai<T>, I2>> cartesian(final OpsElement<I2> e);

	OpsRaiCollection<T> combine();
}
