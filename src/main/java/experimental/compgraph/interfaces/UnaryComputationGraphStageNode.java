
package experimental.compgraph.interfaces;

public interface UnaryComputationGraphStageNode<I, O> extends UnaryComputationGraphNode<I, O>,
	ComputationGraphStageNode<O>
{

	ComputationGraphNode<I> getParent();

	void setParent(ComputationGraphNode<I> parent);
}
