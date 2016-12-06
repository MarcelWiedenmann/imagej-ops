
package experimental.compgraph.node;

import java.util.function.Function;

import experimental.compgraph.CompgraphUnaryNode;
import experimental.compgraph.DataHandle;

public interface Map<IN extends DataHandle<I, ?>, I, O, OUT extends DataHandle<O, ?>> extends
	CompgraphUnaryNode<IN, I, O, OUT>
{

	Function<? super I, O> func();
}
