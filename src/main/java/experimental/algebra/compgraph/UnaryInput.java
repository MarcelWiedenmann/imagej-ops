
package experimental.algebra.compgraph;

import experimental.compgraph.ComputationGraphNode;

public interface UnaryInput<E> extends Input<E> {

	ComputationGraphNode<?, ? extends Output<E>> source();
}
