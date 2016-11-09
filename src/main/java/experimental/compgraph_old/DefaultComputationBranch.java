
package experimental.compgraph_old;

import java.util.Iterator;

import net.imagej.ops.special.function.UnaryFunctionOp;

import experimental.compgraph.ComputationGraphNode;
import experimental.compgraph_old.interfaces.UnaryComputationGraphInputNode;
import experimental.compgraph_old.interfaces.UnaryComputationGraphNode;
import experimental.compgraph_old.interfaces.UnaryComputationGraphStageNode;

public class DefaultComputationBranch<I, O> implements ComputationBranch<I, O> {

	private final int length;
	private final UnaryComputationGraphInputNode<I, ?> start;
	private final UnaryComputationGraphNode<?, O> end;

	public DefaultComputationBranch(final UnaryFunctionOp<I, O> func) {
		length = 1;
		final DefaultUnaryComputationGraphInputNode<I, O> node = new DefaultUnaryComputationGraphInputNode<>(func);
		start = node;
		end = node;
	}

	public <IO> DefaultComputationBranch(final ComputationBranch<I, IO> branch, final UnaryFunctionOp<IO, O> func) {
		length = branch.getLength() + 1;
		final ComputationBranch<I, IO> branchCopy = branch.copy();
		start = branchCopy.getStartNode();
		end = new DefaultUnaryComputationGraphStageNode<>(branchCopy.getEndNode(), func);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <IO> DefaultComputationBranch(final UnaryFunctionOp<I, IO> func, final ComputationBranch<IO, O> branch) {
		length = branch.getLength() + 1;
		final ComputationBranch<IO, O> branchCopy = branch.copy();
		start = new DefaultUnaryComputationGraphInputNode<>(func);
		final UnaryComputationGraphStageNode<IO, ?> join = ComputationGraphNodeConverter.convertToStageNode(
			(UnaryComputationGraphInputNode<I, IO>) start, branchCopy.getStartNode());
		if (branchCopy.getLength() > 1) {
			end = branchCopy.getEndNode();
			UnaryComputationGraphStageNode<?, ?> joinChild = (UnaryComputationGraphStageNode<?, ?>) end;
			for (int i = 1; i < branchCopy.getLength() - 1; i++) {
				joinChild = (UnaryComputationGraphStageNode<?, ?>) joinChild.getParent();
			}
			joinChild.setParent((ComputationGraphNode) join);
		}
		else {
			end = (UnaryComputationGraphStageNode<IO, O>) join;
		}
	}

	@SuppressWarnings("unchecked")
	private DefaultComputationBranch(final DefaultComputationBranch<I, O> branch) {
		length = branch.getLength();
		end = (UnaryComputationGraphNode<?, O>) branch.getEndNode().copy();
		ComputationGraphNode<?> startNode = end;
		for (int i = 1; i < branch.getLength(); i++) {
			startNode = ((UnaryComputationGraphStageNode<?, ?>) startNode).getParent();
		}
		start = (UnaryComputationGraphInputNode<I, ?>) startNode;
	}

	@Override
	public int getLength() {
		return length;
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
	public <II> DefaultComputationBranch<II, O> prepend(final UnaryFunctionOp<II, I> func) {
		return new DefaultComputationBranch<>(func, this);
	}

	@Override
	public <OO> DefaultComputationBranch<I, OO> append(final UnaryFunctionOp<O, OO> func) {
		return new DefaultComputationBranch<>(this, func);
	}

	@Override
	public DefaultComputationBranch<I, O> getFunc() {
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

	@Override
	public Iterator<ComputationGraphNode<?>> iterator() {
		return new DefaultComputationBranchIterator(this);
	}

	public static class DefaultComputationBranchIterator implements Iterator<ComputationGraphNode<?>> {

		private final ComputationBranch<?, ?> branch;
		private ComputationGraphNode<?> current;
		private int i;

		public <I, O> DefaultComputationBranchIterator(final ComputationBranch<I, O> branch) {
			this.branch = branch;
			i = 0;
		}

		@Override
		public boolean hasNext() {
			return i < branch.getLength();
		}

		@Override
		public ComputationGraphNode<?> next() {
			current = current != null ? ((UnaryComputationGraphStageNode<?, ?>) current).getParent() : branch.getEndNode();
			i++;
			return current;
		}
	}
}
