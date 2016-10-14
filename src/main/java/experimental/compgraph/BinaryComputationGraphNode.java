
package experimental.compgraph;

import net.imagej.ops.special.function.BinaryFunctionOp;

public interface BinaryComputationGraphNode<I1, I2, O> extends ComputationGraphNode<O> {

	ComputationGraphNode<I1> getFirstParent();

	ComputationGraphNode<I2> getSecondParent();

	BinaryFunctionOp<I1, I2, O> getOp();

	@Override
	BinaryComputationGraphNode<I1, I2, O> copy();
}
