
package experimental.compgraph.interfaces;

import net.imagej.ops.special.function.BinaryFunctionOp;

public interface BinaryComputationGraphInputNode<I1, I2, O> extends BinaryComputationGraphNode<I1, I2, O>,
	ComputationGraphInputNode<O>, BinaryFunctionOp<I1, I2, O>
{

	@Override
	@SuppressWarnings("unchecked")
	default BinaryComputationGraphInputNode<I1, I2, O> getIndependentInstance() {
		return (BinaryComputationGraphInputNode<I1, I2, O>) copy();
	}

	@Override
	default O out() {
		return compute2(in1(), in2());
	}
}
