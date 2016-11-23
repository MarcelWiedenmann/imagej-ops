
package experimental.compgraph;

import net.imagej.ops.special.function.UnaryFunctionOp;
import net.imglib2.util.Pair;

public interface BinaryEdge<IO1, IO2> extends Edge<Pair<IO1, IO2>> {

	UnaryEdge<IO1> first();

	UnaryEdge<IO2> second();

	Node<?, ? extends UnaryFunctionOp<?, IO1>, ? extends UnaryEdge<IO1>> firstSource();

	Node<?, ? extends UnaryFunctionOp<?, IO2>, ? extends UnaryEdge<IO2>> secondSource();
}
