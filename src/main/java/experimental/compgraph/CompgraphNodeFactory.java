
package experimental.compgraph;

import java.util.function.BiFunction;
import java.util.function.Function;

import experimental.compgraph.algebra.DefaultMap;
import experimental.compgraph.algebra.DefaultReduce;
import experimental.compgraph.algebra.Map;
import experimental.compgraph.algebra.Reduce;

public class CompgraphNodeFactory {

	public static <IN extends CompgraphEdge<I>, I, O, OUT extends CompgraphSingleEdge<O>>
		CompgraphNode<IN, ? extends Map<IN, I, O, OUT>, OUT> map(final IN in, final Function<? super I, O> f)
	{
		final DefaultCompgraphNode<IN, DefaultMap<IN, I, O, OUT>, OUT> n = new DefaultCompgraphNode<>(in, new DefaultMap<>(
			f));
		return n;
	}

	public static <IN extends CompgraphEdge<I>, I, O, OUT extends CompgraphSingleEdge<O>>
		CompgraphNode<IN, ? extends Reduce<IN, I, O, OUT>, OUT> reduce(final IN in, final O memo,
			final BiFunction<O, ? super I, O> f, final BiFunction<O, O, O> merge)
	{
		final DefaultCompgraphNode<IN, DefaultReduce<IN, I, O, OUT>, OUT> n = new DefaultCompgraphNode<>(in,
			new DefaultReduce<>(memo, f, merge));
		return n;
	}

	// TODO: Change join?: [Two unary inputs & BiFunction --> One unary output] (simpler/"more natural" to implement,
	// avoids pair collections/streams)
}
