
package experimental.compgraph.tiling.node;

import net.imglib2.RandomAccessibleInterval;

import experimental.compgraph.CompgraphInputNode;
import experimental.compgraph.tiling.TilingDataHandle;

public interface TilingInputNode<I> extends CompgraphInputNode<RandomAccessibleInterval<I>, TilingDataHandle<I>> {

	// NB: Marker interface.
}
