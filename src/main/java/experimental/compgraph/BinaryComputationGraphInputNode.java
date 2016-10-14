
package experimental.compgraph;

import net.imagej.ops.special.function.BinaryFunctionOp;

public interface BinaryComputationGraphInputNode<I1, I2, O> extends BinaryComputationGraphNode<I1, I2, O>,
	ComputationGraphInputNode<O>, BinaryFunctionOp<I1, I2, O>
{

	@Override
	default ComputationGraphNode<I1> getFirstParent() {
		// NB: Input nodes do not have parents.
		return null;
	}

	@Override
	default ComputationGraphNode<I2> getSecondParent() {
		// NB: Input nodes do not have parents.
		return null;
	}

	@Override
	BinaryComputationGraphInputNode<I1, I2, O> copy();

	@Override
	default O out() {
		return compute2(in1(), in2());
	}

	@Override
	default BinaryComputationGraphInputNode<I1, I2, O> getIndependentInstance() {
		return copy();
	}
}
