
package experimental.cache;

import java.util.function.Function;

import net.imglib2.RandomAccessibleInterval;
import net.imglib2.util.Pair;

import experimental.compgraph.channels.OpsCollection;
import experimental.compgraph.channels.OpsElement;
import experimental.compgraph.channels.OpsGrid;
import experimental.compgraph.channels.rai.OpsRai;
import experimental.compgraph.channels.rai.OpsRaiCollection;
import experimental.compgraph.channels.rai.OpsRaiGrid;

public interface OpsTiling<T> extends OpsRaiGrid<T> {

	@Override
	<O> OpsGrid<O> map(Function<? super OpsRai<T>, O> f);

	<O> OpsTiling<O> tileMap(Function<? super OpsRai<T>, ? extends RandomAccessibleInterval<O>> f);

	@Override
	OpsGrid<OpsTiling<T>> tile(long[] tilesPerDimension, long[] overlap);

	@Override
	<I2> OpsCollection<Pair<OpsRai<T>, I2>> cartesian(final OpsCollection<I2> c);

	<I2> OpsGrid<Pair<OpsRai<T>, I2>> cartesian(final OpsElement<I2> e);

	OpsRaiCollection<T> combine();
}
