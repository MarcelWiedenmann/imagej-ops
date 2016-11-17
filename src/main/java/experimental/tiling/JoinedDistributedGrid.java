
package experimental.tiling;

import net.imagej.ops.special.function.BinaryFunctionOp;
import net.imglib2.util.Pair;

import experimental.compgraph.BinaryAsUnaryFunctionOp;

public interface JoinedDistributedGrid<I1, I2, O1, O2> extends DistributedGrid<Pair<I1, I2>, Pair<O1, O2>>,
	JoinedDistributedCollection<I1, I2, O1, O2>
{

	@Override
	DistributedGrid<I1, O1> first();

	@Override
	DistributedGrid<I2, O2> second();

	@Override
	default <OO> DistributedGrid<Pair<I1, I2>, OO> map(final BinaryFunctionOp<O1, O2, OO> f) {
		return map(new BinaryAsUnaryFunctionOp<>(f));
	}
}
