
package experimental.compgraph;

import net.imagej.ops.special.function.UnaryFunctionOp;

public class DefaultComputationBranch<I, O> extends AbstractComputationBranchInputNode<I, O> implements
	ComputationBranch<I, O>
{

	private final ComputationBranchInputNode<I, ?> start;
	private final UnaryComputationGraphNode<?, O> end;

	public DefaultComputationBranch(final UnaryFunctionOp<I, O> func) {
		super(func);
		final DefaultComputationBranchInputNode<I, O> node = new DefaultComputationBranchInputNode<>(func);
		start = node;
		end = node;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <O2> DefaultComputationBranch(final ComputationBranch<I, O2> branch, final UnaryFunctionOp<O2, O> func) {
		super(null);
		start = branch.getStartNode();
		end = new DefaultComputationBranchEndNode<>(func, new DefaultComputationBranchNode(branch.getEndNode().getParent(),
			branch.getEndNode().getOp()));
	}

	public <I1> DefaultComputationBranch(final UnaryFunctionOp<I, I1> func, final ComputationBranch<I1, O> branch) {
		super(null);
		start = new DefaultComputationBranchStartNode<>(func);
		end = branch.getEndNode();
	}

	@Override
	public O compute1(final I input) {
		start.setInput(input);
		return end.out();
	}

	@Override
	public ComputationBranchInputNode<I, ?> getStartNode() {
		return start;
	}

	@Override
	public UnaryComputationGraphNode<?, O> getEndNode() {
		return end;
	}

	@Override
	public <I2> ComputationBranch<I2, O> concat(final UnaryFunctionOp<I2, I> func) {
		return new DefaultComputationBranch<>(func, this);
	}

	@Override
	public <O2> ComputationBranch<I, O2> preConcat(final UnaryFunctionOp<O, O2> func) {
		return new DefaultComputationBranch<>(this, func);
	}

	@Override
	public UnaryComputationGraphNode<I, O> copy() {
		// TODO Auto-generated method stub
		return null;
	}
}
