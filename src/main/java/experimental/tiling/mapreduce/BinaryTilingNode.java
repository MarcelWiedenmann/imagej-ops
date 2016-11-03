
package experimental.tiling.mapreduce;

import net.imagej.ops.special.function.BinaryFunctionOp;
import net.imglib2.util.Pair;

public interface BinaryTilingNode<E1, E2> extends UnaryTilingNode<Pair<E1, E2>> {

	UnaryTilingNode<E1> first();

	UnaryTilingNode<E2> second();

	<O> UnaryTilingNode<O> map(BinaryFunctionOp<E1, E2, O> f);
}
