
package experimental.tiling;

import net.imagej.ops.special.function.BinaryFunctionOp;
import net.imglib2.util.Pair;

import experimental.compgraph.BinaryAsUnaryFunctionOp;

public interface JoinedLazyCollection<I1, I2, O1, O2> extends OpsList<Pair<I1, I2>, Pair<O1, O2>> {

	OpsList<I1, O1> first();

	OpsList<I2, O2> second();

	default <OO> OpsList<Pair<I1, I2>, OO> append(final BinaryFunctionOp<O1, O2, OO> f) {
		return append(new BinaryAsUnaryFunctionOp<>(f));
	}
}
