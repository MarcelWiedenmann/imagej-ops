
package experimental.tiling;

import java.util.Iterator;
import java.util.function.Function;

import net.imagej.ops.special.function.BinaryFunctionOp;
import net.imagej.ops.special.function.UnaryFunctionOp;
import net.imglib2.RandomAccessibleInterval;

public interface DistributedGrid<I, O> extends DistributedCollection<I, O>, RandomAccessibleInterval<O> {

	<OO> DistributedGrid<O, OO> elementwise(UnaryFunctionOp<O, OO> func);

	<OO> Iterator<PairResult<O, OO>> pairwise(BinaryFunctionOp<O, O, OO> func);

	DistributedGrid<I, DistributedGrid<I, O>> group(Function<long[], Long> func);

	DistributedGrid<I, DistributedGrid<I, O>> blockify(long[] blockSize, long[] offset);

	@Override
	<OO> DistributedGrid<I, OO> map(UnaryFunctionOp<O, OO> f);
}
