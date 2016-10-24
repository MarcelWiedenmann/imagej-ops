
package experimental.compgraph.implementations;

import net.imagej.ops.special.function.UnaryFunctionOp;

import experimental.compgraph.interfaces.ComputationBranch;
import experimental.compgraph.interfaces.ComputationGraphNode;
import experimental.compgraph.interfaces.UnaryComputationGraphInputNode;
import experimental.compgraph.interfaces.UnaryComputationGraphNode;
import experimental.compgraph.interfaces.UnaryComputationGraphStageNode;

public class DefaultComputationBranch<I, O> implements ComputationBranch<I, O> {

	private final UnaryComputationGraphInputNode<I, ?> start;
	private final UnaryComputationGraphNode<?, O> end;

	public DefaultComputationBranch(final UnaryFunctionOp<I, O> func) {
		final DefaultComputationBranchInputNode<I, O> node = new DefaultComputationBranchInputNode<>(func);
		start = node;
		end = node;
	}

	public <IO> DefaultComputationBranch(final ComputationBranch<I, IO> branch, final UnaryFunctionOp<IO, O> func) {
		final ComputationBranch<I, IO> branchCopy = branch.copyUpstream();
		start = branchCopy.getStartNode();
		end = new DefaultComputationBranchStageNode<>(branchCopy.getEndNode(), func);
	}

	public <IO> DefaultComputationBranch(final UnaryFunctionOp<I, IO> func, final ComputationBranch<IO, O> branch) {
		final ComputationBranch<IO, O> branchCopy = branch.copyUpstream();
		start = new DefaultComputationBranchInputNode<>(func);
		end = branchCopy.getEndNode();

		UnaryComputationGraphStageNode<?, ?> joinNode = (UnaryComputationGraphStageNode<?, ?>) end;

		while (joinNode.getParent() != branchCopy.getStartNode()) {
			joinNode = (UnaryComputationGraphStageNode<?, ?>) joinNode.getParent();
		}

		final UnaryComputationGraphStageNode<IO, ?> oldStartAsStage = ComputationGraphNodeConverter.convertToStageNode(
			(UnaryComputationGraphInputNode<I, IO>) start, branchCopy.getStartNode());

		joinNode.setParent(oldStartAsStage);
	}

	private DefaultComputationBranch(final DefaultComputationBranch<I, O> branch) {
		end = (UnaryComputationGraphNode<?, O>) branch.getEndNode().copyUpstream();
		ComputationGraphNode<?> startNode = end;
		while (startNode instanceof UnaryComputationGraphStageNode<?, ?>) {
			startNode = ((UnaryComputationGraphStageNode<?, ?>) startNode).getParent();
		}
		start = (UnaryComputationGraphInputNode<I, ?>) startNode;
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
	public <II> ComputationBranch<II, O> prepend(final UnaryFunctionOp<II, I> func) {
		return new DefaultComputationBranch<>(func, this);
	}

	@Override
	public <OO> ComputationBranch<I, OO> append(final UnaryFunctionOp<O, OO> func) {
		return new DefaultComputationBranch<>(this, func);
	}

	@Override
	public DefaultComputationBranch<I, O> getOp() {
		return this;
	}

	@Override
	public DefaultComputationBranch<I, O> copyUpstream() {
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

//	@Override
//	public Iterator<ComputationGraphNode<?>> iterator() {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
