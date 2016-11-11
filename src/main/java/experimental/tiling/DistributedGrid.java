
package experimental.tiling;

import java.util.function.Function;

import net.imagej.ops.special.function.BinaryFunctionOp;
import net.imagej.ops.special.function.UnaryFunctionOp;
import net.imglib2.RandomAccessibleInterval;

public interface DistributedGrid<I, O> extends DistributedCollection<I, O>, RandomAccessibleInterval<O> {

	<OO> DistributedGrid<O, OO> elementwise(UnaryFunctionOp<O, OO> f);

	// TODO: <OO> Iterator<PairResult<O, OO>> pairwise(BinaryFunctionOp<O, O, OO> f);

	DistributedGrid<I, DistributedGrid<I, O>> group(Function<long[], Long> f);

	DistributedGrid<I, DistributedGrid<I, O>> blockify(long[] size, long[] overlap);

	<OO> DistributedGrid<I, OO> append(UnaryFunctionOp<O, OO> f);

	@Override
	<OO> DistributedGrid<I, OO> map(UnaryFunctionOp<O, OO> f);

	@Override
	<OO> DistributedGrid<I, OO> flatAggregate(BinaryFunctionOp<O, O, OO> f);

	@Override
	<OO> DistributedGrid<I, OO> treeAggregate(BinaryFunctionOp<O, O, OO> f);
}
