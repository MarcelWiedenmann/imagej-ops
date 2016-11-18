
package experimental.tiling;

import net.imagej.ops.special.function.BinaryFunctionOp;
import net.imagej.ops.special.function.UnaryFunctionOp;
import net.imglib2.util.Pair;

import experimental.compgraph.Fork;

public interface DistributedList<E> extends OpsList<E> {

	// -- MapReduce Operations --

	<O> DistributedList<O> map(UnaryFunctionOp<E, O> f);

	<O> DistributedList<O> flatAggregate(BinaryFunctionOp<E, E, O> f);

	<O> DistributedList<O> treeAggregate(BinaryFunctionOp<E, E, O> f);

	// -- LazyCollection --

	@Override
	<O> DistributedList<O> append(UnaryFunctionOp<E, O> f);

	@Override
	Fork<? extends DistributedList<E>> fork();

	@Override
	<E2> DistributedList<Pair<E, E2>> join(final OpsList<E2> c);
}
