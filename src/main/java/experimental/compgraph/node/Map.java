
package experimental.compgraph.node;

import java.util.function.Function;

import experimental.compgraph.CompgraphUnaryNode;
import experimental.compgraph.DataHandle;

public interface Map<I, IN extends DataHandle<I, ?>, O, OUT extends DataHandle<O, ?>> extends
	CompgraphUnaryNode<I, IN, O, OUT>
{

	Function<? super I, O> func();
}
