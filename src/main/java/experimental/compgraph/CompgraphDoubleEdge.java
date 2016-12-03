
package experimental.compgraph;

import net.imglib2.util.Pair;

public interface CompgraphDoubleEdge<INOUT1 extends CompgraphSingleEdge<?>, INOUT2 extends CompgraphSingleEdge<?>>
	extends CompgraphEdge<Pair<INOUT1, INOUT2>>
{

	INOUT1 first();

	INOUT2 second();

	CompgraphNode<?, ?, INOUT1> firstSource();

	CompgraphNode<?, ?, INOUT2> secondSource();
}
