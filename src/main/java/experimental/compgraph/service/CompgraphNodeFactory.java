
package experimental.compgraph.service;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

import experimental.compgraph.CompgraphSingleEdge;
import experimental.compgraph.DataHandle;
import experimental.compgraph.channel.collection.img.OpsTile;
import experimental.compgraph.channel.collection.img.OpsTiling;
import experimental.compgraph.node.Filter;
import experimental.compgraph.node.Map;
import experimental.compgraph.node.Reduce;
import experimental.compgraph.tiling.node.LocalTilingMapNode;

public interface CompgraphNodeFactory {

	<I, O> Map<I, ? extends DataHandle<I, ?>, O, ? extends DataHandle<O, ?>> map(CompgraphSingleEdge<I> in,
		Function<? super I, O> f);

	<I, O> LocalTilingMapNode<I, O> mapTile(OpsTiling<I> in, Function<? super OpsTile<I>, OpsTile<O>> f);

	<I, O> Reduce<I, ? extends DataHandle<I, ?>, O, ? extends DataHandle<O, ?>> reduce(CompgraphSingleEdge<I> in, O memo,
		BiFunction<O, ? super I, O> f, BinaryOperator<O> merge);

	<I> Filter<I, ? extends DataHandle<I, ?>> filter(CompgraphSingleEdge<I> in, Predicate<? super I> f);

//	public <IN extends CompgraphEdge<I>, I, O, OUT extends CompgraphSingleEdge<O>>
//		CompgraphNode<IN, ? extends Reduce<IN, I, O, OUT>, OUT> reduce(final IN in, final O memo,
//			final BiFunction<O, ? super I, O> f, final BiFunction<O, O, O> merge)
//	{
//		final DefaultCompgraphNode<IN, DefaultReduce<IN, I, O, OUT>, OUT> n = new DefaultCompgraphNode<>(in,
//			new DefaultReduce<>(memo, f, merge));
//		return n;
//	}

	// TODO: Change join?: [Two unary inputs & BiFunction --> One unary output] (simpler/"more natural" to implement,
	// avoids pair collections/streams)

	// TODO: add ALL the algebra!
}
