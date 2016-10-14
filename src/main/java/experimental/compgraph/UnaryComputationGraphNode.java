
package experimental.compgraph;

import net.imagej.ops.special.function.UnaryFunctionOp;

public interface UnaryComputationGraphNode<I, O> extends ComputationGraphNode<O> {

	ComputationGraphNode<I> getParent();

	UnaryFunctionOp<I, O> getOp();

	@Override
	UnaryComputationGraphNode<I, O> copy();
}
