
package experimental.compgraph.implementations;

import net.imagej.ops.special.function.UnaryFunctionOp;

import experimental.compgraph.interfaces.ComputationBranch;
import experimental.compgraph.interfaces.UnaryComputationGraphInputNode;
import experimental.compgraph.interfaces.UnaryComputationGraphNode;

public class DefaultComputationBranch<I, O> implements ComputationBranch<I, O> {

	private final ComputationBranchInputNode<I, ?> start;
	private final ComputationBranchNode<?, O> end;

	public DefaultComputationBranch(final UnaryFunctionOp<I, O> func) {
		final DefaultComputationBranchInputNode<I, O> node = new DefaultComputationBranchInputNode<>(func);
		start = node;
		end = node;
	}

	public <IO> DefaultComputationBranch(final ComputationBranch<I, IO> branch, final UnaryFunctionOp<IO, O> func) {
		final ComputationBranch<I, IO> branchCopy = branch.copy();
		start = branchCopy.getStartNode();
		end = new DefaultComputationBranchStageNode<>(branchCopy.getEndNode(), func);
	}

	public <IO> DefaultComputationBranch(final UnaryFunctionOp<I, IO> func, final ComputationBranch<IO, O> branch) {
		final ComputationBranch<IO, O> branchCopy = branch.copy();
		start = new DefaultComputationBranchInputNode<>(func);
		// FIXME: this just works if start is <IO,IO> ...
		branchCopy.getStartNode().getChild().setParent(start);
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
