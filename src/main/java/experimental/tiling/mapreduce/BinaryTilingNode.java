
package experimental.tiling.mapreduce;

import net.imglib2.util.Pair;

import experimental.compgraph.interfaces.ComputationGraphNode;
import experimental.compgraph.interfaces.ComputationGraphNode.BinaryInput;

public interface BinaryTilingNode<E1, E2> extends ComputationGraphNode<BinaryInput<?, ?>, Pair<E1, E2>> {

	ComputationGraphNode<UnaryInput<?>, E1> first();

	ComputationGraphNode<UnaryInput<?>, E2> second();
}
