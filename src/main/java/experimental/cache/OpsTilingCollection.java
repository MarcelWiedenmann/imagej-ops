
package experimental.cache;

import java.util.function.Function;

import net.imglib2.RandomAccessibleInterval;

import experimental.compgraph.channels.OpsCollection;
import experimental.compgraph.channels.OpsGrid;
import experimental.compgraph.channels.OpsListCollection;
import experimental.compgraph.channels.rai.OpsRai;

public interface OpsTilingCollection<T> extends OpsListCollection<OpsRai<T>, OpsTiling<T>> {

	<O> OpsTilingCollection<O> mapTile(Function<? super OpsRai<T>, RandomAccessibleInterval<O>> f);

	// TODO: nesting?
	<O> OpsCollection<OpsGrid<O>> mapTileTo(Function<? super OpsRai<T>, O> f);

}
