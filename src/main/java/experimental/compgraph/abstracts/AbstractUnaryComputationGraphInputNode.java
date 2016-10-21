
package experimental.compgraph.abstracts;

import net.imagej.ops.special.function.UnaryFunctionOp;

import experimental.compgraph.interfaces.UnaryComputationGraphInputNode;

public abstract class AbstractUnaryComputationGraphInputNode<I, O> extends AbstractUnaryComputationGraphNode<I, O>
	implements UnaryComputationGraphInputNode<I, O>
{

	private I input;

	public AbstractUnaryComputationGraphInputNode(final UnaryFunctionOp<I, O> func) {
		this(func, null);
	}

	public AbstractUnaryComputationGraphInputNode(final UnaryFunctionOp<I, O> func, final I input) {
		super(func);
		this.input = input;
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
		return getOp().compute1(input);
	}
}
