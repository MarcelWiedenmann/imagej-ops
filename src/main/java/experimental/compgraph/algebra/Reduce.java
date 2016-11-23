
package experimental.compgraph.algebra;

import java.util.function.BiFunction;
import java.util.function.Function;

import experimental.compgraph.Edge;
import experimental.compgraph.UnaryEdge;

public interface Reduce<I, IN extends Edge<I>, O, OUT extends UnaryEdge<O>> extends Function<IN, OUT> {

	BiFunction<O, I, O> func();
}
