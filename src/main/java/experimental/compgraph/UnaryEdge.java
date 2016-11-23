
package experimental.compgraph;

import net.imagej.ops.special.function.UnaryFunctionOp;

public interface UnaryEdge<IO> extends Edge<IO> {

	Node<?, ? extends UnaryFunctionOp<?, IO>, ? extends UnaryEdge<IO>> source();
}
