
package experimental.compgraph.interfaces;

import net.imagej.ops.OpEnvironment;
import net.imagej.ops.special.function.NullaryFunctionOp;

public interface ComputationGraphInputNode<O> extends ComputationGraphNode<O>, NullaryFunctionOp<O> {

	@Override
	default ComputationGraphInputNode<O> getIndependentInstance() {
		return (ComputationGraphInputNode<O>) copy();
	}

	@Override
	default OpEnvironment ops() {
		// TODO: Underlying question: What is the relationship between our computation graph and the op environment?
		throw new UnsupportedOperationException("Not supported - not yet implemented.");
	}

	@Override
	default void setEnvironment(final OpEnvironment ops) {
		// TODO: Delegating the op environment to our inner function may corrupt other nodes that also reference the
		// function. So this is not really an option - what to do?
		throw new UnsupportedOperationException("Not supported - not yet implemented.");
	}
}
