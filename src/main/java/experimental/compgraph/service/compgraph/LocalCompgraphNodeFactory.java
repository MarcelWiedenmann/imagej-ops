
package experimental.compgraph.service.compgraph;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

import experimental.compgraph.CompgraphSingleEdge;
import experimental.compgraph.DataHandle;
import experimental.compgraph.channel.collection.img.OpsTile;
import experimental.compgraph.channel.collection.img.OpsTiling;
import experimental.compgraph.node.Filter;
import experimental.compgraph.node.LocalFlatFilter;
import experimental.compgraph.node.LocalFlatMap;
import experimental.compgraph.node.LocalFlatReduce;
import experimental.compgraph.node.Map;
import experimental.compgraph.node.Reduce;
import experimental.compgraph.tiling.node.LocalTilingMapNode;

public class LocalCompgraphNodeFactory implements CompgraphNodeFactory {

	// TODO: We may need some Ops-like matching system to match interfaces and
	// their respective optimal implementation.

	@Override
	public <I, O> Map<I, ? extends DataHandle<I, ?>, O, ? extends DataHandle<O, ?>> map(final CompgraphSingleEdge<I> in,
			final Function<? super I, O> f) {
		final LocalFlatMap<I, O> map = new LocalFlatMap<>(in, f);
		in.parent().context().inject(map);
		return map;
	}

	@Override
	public <I, O> LocalTilingMapNode<I, O> mapTile(final OpsTiling<I> in,
			final Function<? super OpsTile<I>, OpsTile<O>> f) {

		final LocalTilingMapNode<I, O> map = new LocalTilingMapNode<>(in, f);
		in.parent().context().inject(map);
		return map;
	}

	@Override
	public <I, O> Reduce<I, ? extends DataHandle<I, ?>, O, ? extends DataHandle<O, ?>> reduce(
			final CompgraphSingleEdge<I> in, final O memo, final BiFunction<O, ? super I, O> f,
			final BinaryOperator<O> merge) {
		final LocalFlatReduce<I, O> reduce = new LocalFlatReduce<>(in, memo, f, merge);
		in.parent().context().inject(reduce);
		return reduce;
	}

	@Override
	public <I> Filter<I, ? extends DataHandle<I, ?>> filter(final CompgraphSingleEdge<I> in,
			final Predicate<? super I> f) {
		final LocalFlatFilter<I> filter = new LocalFlatFilter<>(in, f);
		in.parent().context().inject(filter);
		return filter;
	}
}
