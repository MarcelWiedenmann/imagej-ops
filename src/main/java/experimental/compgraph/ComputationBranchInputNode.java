
package experimental.compgraph;

import net.imagej.ops.special.function.UnaryFunctionOp;

public interface ComputationBranchInputNode<I, O> extends UnaryComputationGraphNode<I, O>, UnaryFunctionOp<I, O> {
	// NB: Combination of super-interfaces. Nothing to declare here.
}
