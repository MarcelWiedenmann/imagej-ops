
package experimental.compgraph.interfaces;

public interface UnaryComputationGraphInputNode<I, O> extends UnaryComputationGraphNode<I, O>,
	ComputationGraphInputNode<O>

{
	// NB: Marker interface.
}
