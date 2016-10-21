
package experimental.compgraph.interfaces;

public interface BinaryComputationGraphStageNode<I1, I2, O, P1 extends ComputationGraphNode<I1>, P2 extends ComputationGraphNode<I2>>
	extends BinaryComputationGraphNode<I1, I2, O, P1, P2>, ComputationGraphStageNode<O>

{

	void setFirstParent(P1 parent);

	void setSecondParent(P2 parent);
}
