
package experimental.tiling;

import net.imagej.ops.special.function.BinaryFunctionOp;
import net.imagej.ops.special.function.UnaryFunctionOp;

import experimental.compgraph.Fork;

public interface DistributedList<I, O> extends OpsList<I, O> {

	// -- MapReduce Operations --

	<OO> DistributedList<I, OO> map(UnaryFunctionOp<O, OO> f);

	<OO> DistributedList<I, OO> flatAggregate(BinaryFunctionOp<O, O, OO> f);

	<OO> DistributedList<I, OO> treeAggregate(BinaryFunctionOp<O, O, OO> f);

	// -- LazyCollection --

	@Override
	<OO> DistributedList<I, OO> append(UnaryFunctionOp<O, OO> f);

	@Override
	Fork<? extends DistributedList<I, O>> fork();

	@Override
	<I2, O2, K> JoinedDistributedCollection<I, I2, O, O2> join(final OpsList<I2, O2> c);
}
