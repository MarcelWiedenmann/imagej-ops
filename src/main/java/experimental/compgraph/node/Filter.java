
package experimental.compgraph.node;

import java.util.function.Predicate;

import experimental.compgraph.CompgraphUnaryNode;
import experimental.compgraph.DataHandle;

public interface Filter<IO, INOUT extends DataHandle<IO, ?>> extends CompgraphUnaryNode<IO, INOUT, IO, INOUT> {

	Predicate<? super IO> func();
}
