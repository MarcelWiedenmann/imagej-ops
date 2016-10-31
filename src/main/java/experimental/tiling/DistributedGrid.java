
package experimental.tiling;

import java.util.Iterator;
import java.util.function.Function;

import net.imagej.ops.special.function.BinaryFunctionOp;
import net.imagej.ops.special.function.UnaryFunctionOp;
import net.imglib2.RandomAccessibleInterval;

public interface DistributedGrid<E> extends RandomAccessibleInterval<E> {

	<R> DistributedGrid<R> elementwise(UnaryFunctionOp<E, R> func);

	<R> Iterator<PairResult<E, R>> pairwise(BinaryFunctionOp<E, E, R> func);

	DistributedGrid<DistributedGrid<E>> group(Function<long[], Long> func);

	DistributedGrid<DistributedGrid<E>> blockify(long[] blockSize, long[] offset);
}
