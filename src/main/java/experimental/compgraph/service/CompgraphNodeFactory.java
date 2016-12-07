
package experimental.compgraph.service;

import java.util.function.Function;

import experimental.compgraph.CompgraphSingleEdge;
import experimental.compgraph.DataHandle;
import experimental.compgraph.node.Map;

public interface CompgraphNodeFactory {

	public <IN extends CompgraphSingleEdge<I>, I, O> Map<I, ? extends DataHandle<I, ?>, O, ? extends DataHandle<O, ?>>
		map(final IN in, final Function<? super I, O> f);

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
