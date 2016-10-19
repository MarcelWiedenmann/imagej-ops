
package experimental.compgraph;

public interface BinaryComputationGraphStageNode<I1, I2, O> extends BinaryComputationGraphNode<I1, I2, O> {

	void setFirstParent(ComputationGraphNode<I1> parent);

	void setSecondParent(ComputationGraphNode<I2> parent);

	@Override
	BinaryComputationGraphStageNode<I1, I2, O> copy();
}
