
package experimental.tiling;

import net.imagej.ops.special.function.UnaryFunctionOp;
import net.imglib2.util.Pair;

import experimental.compgraph.ComputationGraph;
import experimental.compgraph.Fork;

public class DefaultOpsList<E> implements OpsList<E> {

	private ComputationGraph<?, E> graph;

	public DefaultOpsList(final UnaryFunctionOp<?, E> f) {
		// TODO instantiate 'graph', add f
		throw new UnsupportedOperationException("not yet implemented");
	}

	private <I> DefaultOpsList(final DefaultOpsList<I> list, final UnaryFunctionOp<I, E> f) {
		graph = list.graph.append(f);
	}

	@Override
	public <O> OpsList<O> append(final UnaryFunctionOp<E, O> f) {
		return new DefaultOpsList<>(this, f);
	}

	@Override
	public Fork<? extends OpsList<E>> fork() {
		return new Fork<>(this);
	}

	@Override
	public <E2> OpsList<Pair<E, E2>> join(final OpsList<E2> c) {
		throw new UnsupportedOperationException("not yet implemented"); // TODO - join with or without binary op?
	}

}
