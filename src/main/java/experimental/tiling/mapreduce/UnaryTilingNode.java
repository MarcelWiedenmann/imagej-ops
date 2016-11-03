
package experimental.tiling.mapreduce;

import experimental.compgraph.interfaces.ComputationGraphNode;

public interface UnaryTilingNode<E> extends ComputationGraphNode<E> {

	// TODO: Where to put those? (which we'll probably need)
	// public TilingSchema<O> getTilingSchema();
	// public Pair<Interval, O> getSingleTileSchema();
}
