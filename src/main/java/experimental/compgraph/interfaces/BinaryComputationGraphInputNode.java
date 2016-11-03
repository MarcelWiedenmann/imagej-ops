
package experimental.compgraph.interfaces;

public interface BinaryComputationGraphInputNode<I1, I2, O> extends BinaryComputationGraphNode<I1, I2, O>,
	ComputationGraphInputNode<O>
{
	// NB: Marker interface.
}
