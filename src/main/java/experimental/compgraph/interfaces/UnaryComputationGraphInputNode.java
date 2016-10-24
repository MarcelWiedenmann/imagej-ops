
package experimental.compgraph.interfaces;

import net.imagej.ops.special.function.UnaryFunctionOp;

public interface UnaryComputationGraphInputNode<I, O> extends UnaryComputationGraphNode<I, O>,
	ComputationGraphInputNode<O>, UnaryFunctionOp<I, O>

{

	@Override
	default UnaryComputationGraphInputNode<I, O> getIndependentInstance() {
		return (UnaryComputationGraphInputNode<I, O>) copyUpstream();
	}

	@Override
	default O out() {
		return compute1(in());
	}
}
