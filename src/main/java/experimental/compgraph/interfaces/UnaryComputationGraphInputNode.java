
package experimental.compgraph.interfaces;

import net.imagej.ops.special.function.UnaryFunctionOp;

public interface UnaryComputationGraphInputNode<I, O, P extends ComputationGraphNode<I>> extends
	UnaryComputationGraphNode<I, O, P>, ComputationGraphInputNode<O>, UnaryFunctionOp<I, O>

{

	@Override
	default P getParent() {
		// NB: Input nodes do not have parents.
		return null;
	}

	@Override
	default UnaryComputationGraphInputNode<I, O, P> getIndependentInstance() {
		return (UnaryComputationGraphInputNode<I, O, P>) copy();
	}

	@Override
	default O out() {
		return compute1(in());
	}
}
