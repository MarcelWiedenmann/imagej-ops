
package experimental.compgraph.interfaces;

public interface BinaryComputationGraphStageNode<I1, I2, O> extends BinaryComputationGraphNode<I1, I2, O>,
	ComputationGraphStageNode<O>

{

	ComputationGraphNode<I1> getFirstParent();

	ComputationGraphNode<I2> getSecondParent();

	void setFirstParent(final ComputationGraphNode<I1> parent);

	void setSecondParent(final ComputationGraphNode<I2> parent);
}
