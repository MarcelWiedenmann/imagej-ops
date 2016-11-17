
package experimental.tiling;

import net.imagej.ops.special.function.BinaryFunctionOp;
import net.imglib2.util.Pair;

import experimental.compgraph.BinaryAsUnaryFunctionOp;

public interface JoinedDistributedCollection<I1, I2, O1, O2> extends DistributedList<Pair<I1, I2>, Pair<O1, O2>>,
	JoinedLazyCollection<I1, I2, O1, O2>
{

	@Override
	DistributedList<I1, O1> first();

	@Override
	DistributedList<I2, O2> second();

	default <OO> DistributedList<Pair<I1, I2>, OO> map(final BinaryFunctionOp<O1, O2, OO> f) {
		return map(new BinaryAsUnaryFunctionOp<>(f));
	}
}
