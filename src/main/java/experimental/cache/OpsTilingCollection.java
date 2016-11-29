
package experimental.cache;

import java.util.function.Function;

import net.imglib2.RandomAccessibleInterval;

import experimental.compgraph.channel.OpsListCollection;
import experimental.compgraph.channel.collection.OpsCollection;
import experimental.compgraph.channel.collection.OpsGrid;
import experimental.compgraph.channel.rai.OpsRai;

public interface OpsTilingCollection<T> extends OpsListCollection<OpsRai<T>, OpsTiling<T>> {

	<O> OpsTilingCollection<O> mapTile(Function<? super OpsRai<T>, RandomAccessibleInterval<O>> f);

	// TODO: nesting?
	<O> OpsCollection<OpsGrid<O>> mapTileTo(Function<? super OpsRai<T>, O> f);

}
