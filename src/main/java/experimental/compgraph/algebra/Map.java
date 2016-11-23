
package experimental.compgraph.algebra;

import net.imagej.ops.special.function.UnaryFunctionOp;

import experimental.compgraph.Edge;

public interface Map<I, IN extends Edge<I>, O, OUT extends Edge<O>> extends UnaryFunctionOp<IN, OUT> {

	UnaryFunctionOp<I, O> func();
}
