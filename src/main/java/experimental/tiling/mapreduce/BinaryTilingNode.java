
package experimental.tiling.mapreduce;

import net.imglib2.util.Pair;

import experimental.compgraph.BinaryInput;
import experimental.compgraph.ComputationGraphNode;
import experimental.compgraph.UnaryInput;

public interface BinaryTilingNode<E1, E2> extends ComputationGraphNode<BinaryInput<?, ?>, Pair<E1, E2>> {

	ComputationGraphNode<UnaryInput<?>, E1> first();

	ComputationGraphNode<UnaryInput<?>, E2> second();
}
