
package experimental.compgraph.interfaces;

import net.imagej.ops.special.function.BinaryFunctionOp;

public interface BinaryComputationGraphNode<I1, I2, O> extends ComputationGraphNode<O> {

	BinaryFunctionOp<I1, I2, O> getOp();
}
