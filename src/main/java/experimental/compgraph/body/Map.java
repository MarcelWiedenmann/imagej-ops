
package experimental.compgraph.body;

import java.util.function.Function;

import experimental.compgraph.CompgraphDataflowNodeBody;
import experimental.compgraph.Dataflow;

public interface Map<IN extends Dataflow<I, ?>, I, O, OUT extends Dataflow<O, ?>> extends
	CompgraphDataflowNodeBody<IN, I, O, OUT>
{

	Function<? super I, O> func();
}
