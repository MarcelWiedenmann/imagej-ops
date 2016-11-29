
package experimental.compgraph.body;

import java.util.function.BiFunction;

import experimental.compgraph.CompgraphEdge;
import experimental.compgraph.CompgraphNodeBody;
import experimental.compgraph.CompgraphSingleEdge;

public interface Reduce<IN extends CompgraphEdge<I>, I, O, OUT extends CompgraphSingleEdge<O>> extends
	CompgraphNodeBody<IN, OUT>
{

	O memo();

	BiFunction<O, ? super I, O> func();

	BiFunction<O, O, O> merge();
}
