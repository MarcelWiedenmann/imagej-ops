
package experimental.compgraph.tiling;

import net.imglib2.RandomAccessibleInterval;

import experimental.compgraph.CompgraphInputNode;

public interface TilingInputNode<I> extends CompgraphInputNode<RandomAccessibleInterval<I>, TilingDataHandle<I>> {

	// NB: Marker interface.
}
