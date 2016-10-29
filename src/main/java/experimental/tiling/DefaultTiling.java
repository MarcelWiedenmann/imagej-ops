
package experimental.tiling;

import java.util.Iterator;

import net.imagej.ops.special.function.UnaryFunctionOp;

import experimental.compgraph.implementations.DefaultComputationBranch;

public class DefaultTiling<I, O> implements Tiling<I, O> {

	DefaultComputationBranch<I, O> branch;

	public DefaultTiling() {}

	private DefaultTiling(final DefaultComputationBranch<I, O> branch) {
		this.branch = branch;
	}

	@Override
	public <OO> Tiling<I, OO> map(final UnaryFunctionOp<O, OO> map) {
		return new DefaultTiling<I, OO>(branch.append(map));
	}

	@Override
	public <OO> Tiling<I, OO> forwardAggregate(final UnaryFunctionOp<Iterator<O>, OO> aggregate) {
		throw new UnsupportedOperationException("not yet implemented");
	}

	@Override
	public <OO> Tiling<I, OO> treeAggregate(final UnaryFunctionOp<Iterator<O>, OO> aggregate) {
		throw new UnsupportedOperationException("not yet implemented");
	}

	public O compute1(final Iterable<I> input) {
		throw new UnsupportedOperationException("not yet implemented");
	}
}
