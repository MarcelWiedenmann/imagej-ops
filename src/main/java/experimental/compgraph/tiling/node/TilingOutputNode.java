
package experimental.compgraph.tiling.node;

import net.imglib2.RandomAccessibleInterval;

import experimental.compgraph.CompgraphOutputNode;
import experimental.compgraph.tiling.TilingDataHandle;

public interface TilingOutputNode<O> extends CompgraphOutputNode<RandomAccessibleInterval<O>, TilingDataHandle<O>> {

	// NB: Marker interface.
}
