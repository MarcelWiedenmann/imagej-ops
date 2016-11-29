
package experimental.compgraph.channels.rai;

import java.util.function.Function;

import net.imglib2.RandomAccessibleInterval;

import experimental.cache.OpsTilingCollection;
import experimental.compgraph.channels.OpsListCollection;

public interface OpsRaiCollection<T> extends OpsListCollection<T, OpsRai<T>> {

	<O> OpsRaiCollection<O> mapRAI(Function<? super OpsRai<T>, ? extends RandomAccessibleInterval<O>> f);

	OpsTilingCollection<T> tile(long[] tilesPerDimension, long[] overlap);
}
