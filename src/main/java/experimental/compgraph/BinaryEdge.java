
package experimental.compgraph;

import net.imglib2.util.Pair;

public interface BinaryEdge<IO1, IO2> extends Edge<Pair<IO1, IO2>> {

	UnaryEdge<IO1> first();

	UnaryEdge<IO2> second();

// FIXME: typing
//	Node<?, ? extends Function<?, IO1>, ? extends UnaryEdge<IO1>> firstSource();
//
//	Node<?, ? extends Function<?, IO2>, ? extends UnaryEdge<IO2>> secondSource();
}
