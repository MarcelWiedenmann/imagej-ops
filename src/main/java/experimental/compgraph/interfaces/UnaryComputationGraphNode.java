
package experimental.compgraph.interfaces;

import net.imagej.ops.special.function.UnaryFunctionOp;

public interface UnaryComputationGraphNode<I, O> extends ComputationGraphNode<O> {

	UnaryFunctionOp<I, O> getOp();
}
