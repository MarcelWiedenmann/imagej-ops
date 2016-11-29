
package experimental.compgraph.body;

import java.util.function.Function;

import experimental.compgraph.CompgraphEdge;
import experimental.compgraph.CompgraphNodeBody;
import experimental.compgraph.CompgraphSingleEdge;

public interface Map<IN extends CompgraphEdge<I>, I, O, OUT extends CompgraphSingleEdge<O>> extends
	CompgraphNodeBody<IN, OUT>
{

	Function<? super I, O> func();
}
