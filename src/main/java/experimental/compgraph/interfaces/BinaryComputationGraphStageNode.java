
package experimental.compgraph.interfaces;

public interface BinaryComputationGraphStageNode<I1, I2, O> extends BinaryComputationGraphNode<I1, I2, O>,
	ComputationGraphStageNode<O>

{

	ComputationGraphNode<I1> getFirstParent();

	ComputationGraphNode<I2> getSecondParent();

	default void setFirstParent(final ComputationGraphNode<I1> parent) {
		final ComputationGraphNode<I1> current = getFirstParent();
		if (parent != current) {
			if (current != null) {
				current.removeChild(this);
			}
			if (parent != null) {
				parent.addChild(this);
			}
		}
	}

	default void setSecondParent(final ComputationGraphNode<I2> parent) {
		final ComputationGraphNode<I2> current = getSecondParent();
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
