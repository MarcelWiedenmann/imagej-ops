
package experimental.compgraph;

import net.imagej.ops.special.function.UnaryFunctionOp;

public interface UnaryComputationGraphInputNode<I, O> extends UnaryComputationGraphNode<I, O>,
	ComputationGraphInputNode<O>, UnaryFunctionOp<I, O>
{

	@Override
	default ComputationGraphNode<I> getParent() {
		// NB: Input nodes do not have parents.
		return null;
	}

	@Override
	UnaryComputationGraphInputNode<I, O> copy();

	@Override
	default O out() {
		return compute1(in());
	}

	@Override
	default UnaryComputationGraphInputNode<I, O> getIndependentInstance() {
		return copy();
	}
}
