
package experimental.tiling;

import java.util.function.Function;

import net.imagej.ops.special.function.BinaryFunctionOp;
import net.imagej.ops.special.function.UnaryFunctionOp;
import net.imglib2.Interval;

import experimental.compgraph.Fork;

public interface DistributedGrid<I, O> extends DistributedCollection<I, O>, Interval /* No RAI: no access allowed. */ {

	<I2, O2, K> JoinedDistributedGrid<I, I2, O, O2> join(DistributedGrid<I2, O2> g);

	// TODO: look at Google doc

	// <OO> DistributedGrid<O, OO> elementwise(UnaryFunctionOp<O, OO> f);

	// <OO> DistributedList<OO> pairs(BinaryFunctionOp<O, O, OO> f);

	// DistributedGrid<I, DistributedGrid<I, O>> group(Function<long[], Long> f);

	// DistributedGrid<I, DistributedGrid<I, O>> blockify(long[] size, long[] overlap);

	// -- DistributedList --

	@Override
	<OO> DistributedGrid<I, OO> map(UnaryFunctionOp<O, OO> f);

	@Override
	<OO> DistributedGrid<I, OO> flatAggregate(BinaryFunctionOp<O, O, OO> f);

	@Override
	<OO> DistributedGrid<I, OO> treeAggregate(BinaryFunctionOp<O, O, OO> f);

	@Override
	Fork<? extends DistributedGrid<I, O>> fork();

	@Override
	<I2, O2, K> JoinedDistributedGrid<I, I2, O, O2> join(LazyCollection<I2, O2> c, Function<O, K> kf1,
		Function<O2, K> kf2);
}
