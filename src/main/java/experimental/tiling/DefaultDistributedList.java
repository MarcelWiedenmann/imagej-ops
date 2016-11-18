
package experimental.tiling;

import net.imagej.ops.special.function.BinaryFunctionOp;
import net.imagej.ops.special.function.UnaryFunctionOp;
import net.imglib2.util.Pair;

import experimental.compgraph.ComputationGraph;
import experimental.compgraph.Fork;
import experimental.tiling.mapreduce.UnaryDistributable;

public class DefaultDistributedList<E> implements DistributedList<E> {

	private final ComputationGraph<?, E> graph;

	public DefaultDistributedList(final UnaryFunctionOp<?, E> f) {
		// TODO instantiate 'graph', add f
		throw new UnsupportedOperationException("not yet implemented");
	}

	private <I> DefaultDistributedList(final DefaultDistributedList<I> list, final UnaryFunctionOp<I, E> f) {
		// give 'f' semantic, i.e. change type from func to "mapNode", "aggrNode", etc.
		if (f instanceof MapNode && list.graph.last() instanceof MapMetaNode) {
			((MapMetaNode) list.graph.last()).append(f);
		}
		else {
			graph = list.graph.append(f);
		}
	}

	// TODO:
	// two things differ between map/aggr/...:
	// - type of node added (MapNode, AggregateNode)
	// - location of addition (end of graph, within meta node)
	// --> create helper methods for those decisions

	protected MapNode setupMapNode(..) {

	}

	protected AggregateNode setupAggregateNode(..) {

	}

	protected ComputationGraph addMapNodeToGraph(..) {

	}

	// ...

	@Override
	public <O> DistributedList<O> map(final UnaryFunctionOp<E, O> f) {
		if (f instanceof UnaryDistributable) {
			return ((UnaryDistributable<E, O>) f).getDistributionPlan(this);
		}
		else {
			return new DefaultDistributedList<>(this, f);
		}
	}

	@Override
	public <O> DistributedList<O> flatAggregate(final BinaryFunctionOp<E, E, O> f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O> DistributedList<O> treeAggregate(final BinaryFunctionOp<E, E, O> f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O> DistributedList<O> append(final UnaryFunctionOp<E, O> f) {
		return map(f);
	}

	@Override
	public Fork<? extends DistributedList<E>> fork() {
		return new Fork<>(this);
	}

	@Override
	public <E2> DistributedList<Pair<E, E2>> join(final OpsList<E2> c) {
		// TODO Auto-generated method stub
		return null;
	}

}
