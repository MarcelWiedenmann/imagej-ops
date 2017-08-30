
package experimental.compgraph.node;

import java.util.function.BiFunction;

import experimental.compgraph.CompgraphUnaryNode;
import experimental.compgraph.DataHandle;

public interface Reduce<I, IN extends DataHandle<I, ?>, O, OUT extends DataHandle<O, ?>> extends
	CompgraphUnaryNode<I, IN, O, OUT>
{

	O memo();

	BiFunction<O, ? super I, O> func();

	BiFunction<O, O, O> merge();
}
