
package experimental.compgraph;

import net.imglib2.util.Pair;

public interface CompgraphDoubleEdge<INOUT1 extends CompgraphSingleEdge<?>, INOUT2 extends CompgraphSingleEdge<?>>
	extends CompgraphEdge<Pair<INOUT1, INOUT2>>
{

	INOUT1 first();

	INOUT2 second();

	// FIXME: typing
	CompgraphNode<?, ? extends CompgraphNodeBody<?, INOUT1>, INOUT1> firstSource();

	CompgraphNode<?, ? extends CompgraphNodeBody<?, INOUT2>, INOUT2> secondSource();
}
