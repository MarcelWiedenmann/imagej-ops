
package experimental.compgraph.interfaces;

public interface UnaryComputationGraphStageNode<I, O> extends UnaryComputationGraphNode<I, O>,
	ComputationGraphStageNode<O>
{

	ComputationGraphNode<I> getParent();

	default void setParent(final ComputationGraphNode<I> parent) {
		final ComputationGraphNode<I> current = getParent();
		if (parent != current) {
			if (current != null) {
				current.removeChild(this);
			}
			if (parent != null) {
				parent.addChild(this);
			}
		}
	}
}
