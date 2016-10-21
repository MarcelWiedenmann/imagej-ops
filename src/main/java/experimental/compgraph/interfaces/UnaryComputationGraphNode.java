
package experimental.compgraph.interfaces;

import net.imagej.ops.special.function.UnaryFunctionOp;

public interface UnaryComputationGraphNode<I, O, P extends ComputationGraphNode<I>> extends ComputationGraphNode<O> {

	P getParent();

	UnaryFunctionOp<I, O> getOp();
}
