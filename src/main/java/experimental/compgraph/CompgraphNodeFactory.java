
package experimental.compgraph;

import java.util.function.Function;

import experimental.compgraph.body.DefaultMap;
import experimental.compgraph.body.Map;

public class CompgraphNodeFactory {

	// TODO: convert to service

	public <IN extends CompgraphEdge<I>, I, O, OUT extends CompgraphSingleEdge<O>>
		CompgraphNode<IN, ? extends Map<? extends Dataflow<I, ?>, I, O, ? extends Dataflow<O, ?>>, OUT> map(final IN in,
			final Function<? super I, O> f)
	{
		// TODO: we need some Ops-like matching to match interfaces such as 'Map' and their respective implementations given
		// the target execution environment ('DefaultMap' for local execution).

		final DefaultCompgraphDataflowNode<IN, I, DefaultMap<DefaultDataflow<I>, I, O, DefaultDataflow<O>>, O, OUT> n =
			new DefaultCompgraphDataflowNode<>(in, new DefaultMap<>(f), this);
		return n;
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
