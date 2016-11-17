
package experimental.tiling;

import net.imagej.ops.special.function.BinaryFunctionOp;
import net.imagej.ops.special.function.UnaryFunctionOp;

public interface Tiling<I, O> extends DistributedGrid<Tile<I>, Tile<O>> {

	// FIXME: clash: grid-of-grid <--> function types ("OO")
	// what we want:
	<OO> Tiling<I, OO> append(UnaryFunctionOp<O, OO> f);

	// what we got:
	<OO> DistributedList<Tile<I>, OO> append(UnaryFunctionOp<Tile<O>, Tile<OO>> f);

	@Override
	<OO> Tiling<I, OO> map(UnaryFunctionOp<O, OO> f);

	@Override
	<OO> Tiling<I, OO> flatAggregate(BinaryFunctionOp<O, O, OO> f);

	@Override
	<OO> Tiling<I, OO> treeAggregate(BinaryFunctionOp<O, O, OO> f);
}
