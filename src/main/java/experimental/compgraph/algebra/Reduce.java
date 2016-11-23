
package experimental.compgraph.algebra;

import net.imagej.ops.special.function.BinaryFunctionOp;
import net.imagej.ops.special.function.UnaryFunctionOp;

import experimental.compgraph.Edge;

public interface Reduce<I, IN extends Edge<I>, O, OUT extends Edge<O>> extends UnaryFunctionOp<IN, OUT> {

	BinaryFunctionOp<O, I, O> func();
}
