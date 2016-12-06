
package experimental.compgraph;

import java.util.function.Function;

import experimental.compgraph.node.DefaultMap;
import experimental.compgraph.node.Map;

public class CompgraphNodeFactory {
	// TODO: convert to service

	public <IN extends CompgraphSingleEdge<I>, I, O> Map<? extends DataHandle<I, ?>, I, O, ? extends DataHandle<O, ?>>
		map(final IN in, final Function<? super I, O> f)
	{
		// TODO: We need some Ops-like matching to match interfaces such as 'Map' with their respective implementations
		// given the target execution environment ('DefaultMap' for local execution).

		return new DefaultMap<>(f, in, this);
	}

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
