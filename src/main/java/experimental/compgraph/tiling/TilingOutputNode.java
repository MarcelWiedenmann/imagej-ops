
package experimental.compgraph.tiling;

import net.imglib2.RandomAccessibleInterval;

import experimental.compgraph.CompgraphOutputNode;

public interface TilingOutputNode<O> extends CompgraphOutputNode<RandomAccessibleInterval<O>, TilingDataHandle<O>> {

	// NB: Marker interface.
}
