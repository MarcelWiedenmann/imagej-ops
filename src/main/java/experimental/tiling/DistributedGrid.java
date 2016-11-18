
package experimental.tiling;

import net.imagej.ops.special.function.BinaryFunctionOp;
import net.imagej.ops.special.function.UnaryFunctionOp;
import net.imglib2.Interval;
import net.imglib2.util.Pair;

import experimental.compgraph.Fork;

public interface DistributedGrid<E> extends DistributedList<E>, Interval /* No RAI: no access allowed. */ {

	// TODO: (look at Google doc)

	// <O> DistributedGrid<O> elementwise(UnaryFunctionOp<E, O> f);

	// <O> DistributedList<O> pairs(BinaryFunctionOp<E, E, O> f);

	// DistributedGrid<DistributedGrid<E>> group(Function<long[], Long> f);

	// DistributedGrid<DistributedGrid<E>> blockify(long[] size, long[] overlap);

	// -- DistributedList --

	@Override
	<O> DistributedGrid<O> map(UnaryFunctionOp<E, O> f);

	@Override
	<O> DistributedGrid<O> flatAggregate(BinaryFunctionOp<E, E, O> f);

	@Override
	<O> DistributedGrid<O> treeAggregate(BinaryFunctionOp<E, E, O> f);

	@Override
	Fork<? extends DistributedGrid<E>> fork();

	@Override
	<E2> DistributedGrid<Pair<E, E2>> join(OpsList<E2> g);
}
