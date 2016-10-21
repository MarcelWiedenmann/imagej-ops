
package experimental.compgraph.interfaces;

public interface ComputationBranchNode<I, O> extends UnaryComputationGraphNode<I, O, ComputationBranchNode<?, I>> {

	ComputationBranchStageNode<O, ?> getChild();

	void setChild(ComputationBranchStageNode<O, ?> child);
}
