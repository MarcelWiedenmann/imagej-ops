
package experimental.compgraph;

import net.imagej.ops.special.function.UnaryFunctionOp;

public interface Node<IN extends Edge<?>, BODY extends UnaryFunctionOp<IN, OUT>, OUT extends Edge<?>> {

	IN in();

	BODY body();

	OUT out();
}
