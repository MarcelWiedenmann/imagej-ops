
package experimental.algebra;

import java.util.function.Predicate;

import net.imagej.ops.special.function.BinaryFunctionOp;
import net.imagej.ops.special.function.UnaryFunctionOp;

import experimental.compgraph.Fork;

public interface OpsUnboundedStream<I> {

	// -- First Order Operations --

	<O> OpsUnboundedStream<O> map(UnaryFunctionOp<I, O> f);

	<O> OpsUnboundedStream<O> reduce(BinaryFunctionOp<O, I, O> f, int window);

	OpsUnboundedStream<I> filter(Predicate<I> f);

	Fork<? extends OpsUnboundedStream<I>> fork();

	// TODO: streaming algebra stuff

	// -- Higher Order Operators -

	// collection.map(this)

	// map(f) {
	// n = new Node(this, map(f));
	// return n.out();
	// }
}
