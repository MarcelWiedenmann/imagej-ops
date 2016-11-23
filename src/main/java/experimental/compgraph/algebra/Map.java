
package experimental.compgraph.algebra;

import java.util.function.Function;

import experimental.compgraph.Edge;
import experimental.compgraph.UnaryEdge;

public interface Map<I, IN extends Edge<I>, O, OUT extends UnaryEdge<O>> extends Function<IN, OUT> {

	Function<? super I, O> func();
}
