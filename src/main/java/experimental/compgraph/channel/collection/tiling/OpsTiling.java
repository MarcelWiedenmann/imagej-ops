
package experimental.compgraph.channel.collection.tiling;

import java.util.function.Function;

import net.imglib2.RandomAccessibleInterval;

import experimental.compgraph.channel.collection.nested.OpsNestedGrid;

public interface OpsTiling<T> extends OpsNestedGrid<T, OpsTile<T>> {

	<O> OpsTiling<O> mapTile(Function<? super OpsTile<T>, RandomAccessibleInterval<O>> f);
}
