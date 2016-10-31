
package experimental.tiling.mapreduce;

import net.imagej.ops.special.function.BinaryFunctionOp;
import net.imglib2.util.Pair;

public interface BinaryTilingNode<O1, O2> extends UnaryTilingNode<Pair<O1, O2>> {

	UnaryTilingNode<O1> first();

	UnaryTilingNode<O2> second();

	<O> UnaryTilingNode<O> map(BinaryFunctionOp<O1, O2, O> mapper);
}
