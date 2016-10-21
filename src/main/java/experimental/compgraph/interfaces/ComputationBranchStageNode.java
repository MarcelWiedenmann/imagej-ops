
package experimental.compgraph.interfaces;

public interface ComputationBranchStageNode<I, O> extends ComputationBranchNode<I, O>,
	UnaryComputationGraphStageNode<I, O, ComputationBranchNode<?, I>>
{

	@Override
	void setParent(ComputationBranchNode<?, I> parent);
}
