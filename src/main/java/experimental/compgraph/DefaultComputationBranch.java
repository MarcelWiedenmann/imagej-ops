
package experimental.compgraph;

import net.imagej.ops.special.function.UnaryFunctionOp;

// TODO: abstract class needed?
public class DefaultComputationBranch<I, O> implements ComputationBranch<I, O> {

	private final UnaryComputationGraphInputNode<I, ?> start;
	private final UnaryComputationGraphNode<?, O> end;

	public DefaultComputationBranch(final UnaryFunctionOp<I, O> func) {
		final DefaultUnaryComputationGraphInputNode<I, O> node = new DefaultUnaryComputationGraphInputNode<>(func);
		start = node;
		end = node;
	}

	public <IO> DefaultComputationBranch(final ComputationBranch<I, IO> branch, final UnaryFunctionOp<IO, O> func) {
		final ComputationBranch<I, IO> branchCopy = branch.copy();
		start = branchCopy.getStartNode();
		end = new DefaultUnaryComputationGraphStageNode<>(branchCopy.getEndNode(), func);
	}

	public <IO> DefaultComputationBranch(final UnaryFunctionOp<I, IO> func, final ComputationBranch<IO, O> branch) {
		final ComputationBranch<IO, O> branchCopy = branch.copy();
		start = new DefaultUnaryComputationGraphInputNode<>(func);
		// FIXME: we must establish a binding between start and the body of branch (body = branch \ {start})
		// something like this is needed: branchCopy.getStartNode().getChild().setParent(start);
		end = branchCopy.getEndNode();
	}

	private DefaultComputationBranch(final DefaultComputationBranch<I, O> branch) {
		end = branch.getEndNode().copy();
		// FIXME: something like this is needed: start = end.getRoot();
	}

	@Override
	public UnaryComputationGraphInputNode<I, ?> getStartNode() {
		return start;
	}

	@Override
	public UnaryComputationGraphNode<?, O> getEndNode() {
		return end;
	}

	@Override
	public <II> ComputationBranch<II, O> concat(final UnaryFunctionOp<II, I> func) {
		return new DefaultComputationBranch<>(func, this);
	}

	@Override
	public <OO> ComputationBranch<I, OO> preConcat(final UnaryFunctionOp<O, OO> func) {
		return new DefaultComputationBranch<>(this, func);
	}

	@Override
	public DefaultComputationBranch<I, O> getOp() {
		return this;
	}

	@Override
	public DefaultComputationBranch<I, O> copy() {
		return new DefaultComputationBranch<>(this);
	}

	@Override
	public I in() {
		return start.in();
	}

	@Override
	public void setInput(final I input) {
		start.setInput(input);
	}

	@Override
	public O compute1(final I input) {
		start.setInput(input);
		return end.out();
	}
}
