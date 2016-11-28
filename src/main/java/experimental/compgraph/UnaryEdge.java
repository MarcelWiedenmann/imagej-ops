
package experimental.compgraph;

import java.util.function.Function;

public interface UnaryEdge<IO> extends Edge<IO> {

//	FIXME: typing
	Node<?, ? extends Function<?, ? extends UnaryEdge<IO>>, ? extends UnaryEdge<IO>> source();
}
