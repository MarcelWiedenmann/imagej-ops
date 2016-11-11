
package experimental.tiling;

import net.imagej.ops.Ops.Join;
import net.imagej.ops.special.function.BinaryFunctionOp;
import net.imagej.ops.special.function.UnaryFunctionOp;

public interface DistributedCollection<I, O> extends LazyCollection<I, O> {

	// -- MapReduce Operations --

	<OO> DistributedCollection<I, OO> map(UnaryFunctionOp<O, OO> f);

	<OO> DistributedCollection<I, OO> flatAggregate(BinaryFunctionOp<O, O, OO> f);

	<OO> DistributedCollection<I, OO> treeAggregate(BinaryFunctionOp<O, O, OO> f);

	// -- Graph Operations --

	// TODO: how to model forks and joins? Generic Fork and Join classes are probably easier than implementing explicit
	// fork/join-collections/grids/tilings/etc...
	Fork<DistributedCollection<I, O>> fork();

	<I2, O2, OO> Join<DistributedCollection<I, O>, DistributedCollection<I2, O2>> joinFirst(
		DistributedCollection<I2, O2> c, BinaryFunctionOp<O, O2, OO> f);

	<I2, O2, OO> Join<DistributedCollection<I, O>, DistributedCollection<I2, O2>> joinSecond(
		DistributedCollection<I2, O2> c, BinaryFunctionOp<O2, O, OO> f);
}
