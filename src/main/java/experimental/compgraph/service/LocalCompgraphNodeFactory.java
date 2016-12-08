
package experimental.compgraph.service;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

import experimental.compgraph.CompgraphSingleEdge;
import experimental.compgraph.DataHandle;
import experimental.compgraph.node.Filter;
import experimental.compgraph.node.LocalFilter;
import experimental.compgraph.node.LocalMap;
import experimental.compgraph.node.LocalReduce;
import experimental.compgraph.node.Map;
import experimental.compgraph.node.Reduce;

public class LocalCompgraphNodeFactory implements CompgraphNodeFactory {

	// TODO: We may need some Ops-like matching system to match interfaces and their respective optimal implementation.

	@Override
	public <I, O> Map<I, ? extends DataHandle<I, ?>, O, ? extends DataHandle<O, ?>> map(final CompgraphSingleEdge<I> in,
		final Function<? super I, O> f)
	{
		final LocalMap<I, O> map = new LocalMap<>(in, f);
		in.parent().context().inject(map);
		return map;
	}

	@Override
	public <I, O> Reduce<I, ? extends DataHandle<I, ?>, O, ? extends DataHandle<O, ?>> reduce(
		final CompgraphSingleEdge<I> in, final O memo, final BiFunction<O, ? super I, O> f, final BinaryOperator<O> merge)
	{
		final LocalReduce<I, O> reduce = new LocalReduce<>(in, memo, f, merge);
		in.parent().context().inject(reduce);
		return reduce;
	}

	@Override
	public <I> Filter<I, ? extends DataHandle<I, ?>> filter(final CompgraphSingleEdge<I> in,
		final Predicate<? super I> f)
	{
		final LocalFilter<I> filter = new LocalFilter<>(in, f);
		in.parent().context().inject(filter);
		return filter;
	}
}
