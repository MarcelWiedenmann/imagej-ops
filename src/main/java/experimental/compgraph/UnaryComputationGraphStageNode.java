
package experimental.compgraph;

public interface UnaryComputationGraphStageNode<I, O> extends UnaryComputationGraphNode<I, O> {

	void setParent(ComputationGraphNode<I> parent);

	@Override
	UnaryComputationGraphStageNode<I, O> copy();
}
