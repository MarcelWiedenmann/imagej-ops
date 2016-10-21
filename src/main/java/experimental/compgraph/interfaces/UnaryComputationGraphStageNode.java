
package experimental.compgraph.interfaces;

public interface UnaryComputationGraphStageNode<I, O, P extends ComputationGraphNode<I>> extends
	UnaryComputationGraphNode<I, O, P>, ComputationGraphStageNode<O>
{

	void setParent(P parent);
}
