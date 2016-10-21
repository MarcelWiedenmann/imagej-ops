
package experimental.compgraph.interfaces;

public interface ComputationBranchInputNode<I, O> extends ComputationBranchNode<I, O>,
	UnaryComputationGraphInputNode<I, O, ComputationBranchNode<?, I>>
{
	// NB: Marker interface.
}
