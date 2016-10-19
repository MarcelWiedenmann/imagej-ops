
package experimental.compgraph;

import net.imagej.ops.special.function.UnaryFunctionOp;

public abstract class AbstractUnaryComputationGraphInputNode<I, O> extends AbstractUnaryComputationGraphNode<I, O>
	implements UnaryComputationGraphInputNode<I, O>
{

	// TODO: move basic stuff to super class

	private I input;
	private final UnaryFunctionOp<I, O> func;

	public AbstractUnaryComputationGraphInputNode(final UnaryFunctionOp<I, O> func) {
		this.func = func;
	}

	public AbstractUnaryComputationGraphInputNode(final UnaryFunctionOp<I, O> func, final I input) {
		this.func = func;
		this.input = input;
	}

	@Override
	public UnaryFunctionOp<I, O> getOp() {
		return func;
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
}
