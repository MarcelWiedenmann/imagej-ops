
package experimental.compgraph;

public interface UnaryInput<E> extends Input<E> {

	ComputationGraphNode<?, E> source();
}
