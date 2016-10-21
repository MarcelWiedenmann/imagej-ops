
package experimental.compgraph.interfaces;

import net.imagej.ops.special.function.BinaryFunctionOp;

public interface BinaryComputationGraphInputNode<I1, I2, O, P1 extends ComputationGraphNode<I1>, P2 extends ComputationGraphNode<I2>>
	extends BinaryComputationGraphNode<I1, I2, O, P1, P2>, ComputationGraphInputNode<O>, BinaryFunctionOp<I1, I2, O>
{

	@Override
	default P1 getFirstParent() {
		// NB: Input nodes do not have parents.
		return null;
	}

	@Override
	default P2 getSecondParent() {
		// NB: Input nodes do not have parents.
		return null;
	}

	@Override
	default BinaryComputationGraphInputNode<I1, I2, O, P1, P2> getIndependentInstance() {
		return (BinaryComputationGraphInputNode<I1, I2, O, P1, P2>) copy();
	}

	@Override
	default O out() {
		return compute2(in1(), in2());
	}
}
