
package experimental.compgraph.interfaces;

import net.imagej.ops.special.function.BinaryFunctionOp;

public interface BinaryComputationGraphNode<I1, I2, O, P1 extends ComputationGraphNode<I1>, P2 extends ComputationGraphNode<I2>>
	extends ComputationGraphNode<O>
{

	P1 getFirstParent();

	P2 getSecondParent();

	BinaryFunctionOp<I1, I2, O> getOp();
}
