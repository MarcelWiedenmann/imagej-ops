
package experimental.compgraph;

import net.imagej.ops.OpEnvironment;
import net.imagej.ops.special.function.UnaryFunctionOp;

public abstract class AbstractComputationBranchInputNode<I, O> extends AbstractComputationBranchNode<I, O> implements
	ComputationBranchInputNode<I, O>
{

	private I input;

	public AbstractComputationBranchInputNode(final UnaryFunctionOp<I, O> func) {
		super(func);
	}

	@Override
	public O out() {
		return compute1(input);
	}

	@Override
	public I in() {
		return input;
	}

	@Override
	public void setInput(final I input) {
		this.input = input;
	}

	@Override
	public O compute1(final I input) {
		return func.compute1(input);
	}

	@Override
	public OpEnvironment ops() {
		throw new UnsupportedOperationException("Not supported - not yet implemented.");
	}

	@Override
	public void setEnvironment(final OpEnvironment ops) {
		// TODO: Delegating ops to func may corrupt other nodes that also reference func.
		throw new UnsupportedOperationException("Not supported - not yet implemented.");
	}
}
