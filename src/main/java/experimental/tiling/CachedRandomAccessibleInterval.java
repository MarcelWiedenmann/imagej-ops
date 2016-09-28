
package experimental.tiling;

import net.imglib2.RandomAccessibleInterval;

import experimental.tiling.execution.LazyExecution;
import experimental.tiling.execution.LazyExecutionBranch;

public class CachedRandomAccessibleInterval<I, O> extends
	LazyExecution<RandomAccessibleInterval<I>, RandomAccessibleInterval<O>>
{

	// TODO: mapping betwee generics
	// I input where I extends RandomAccessibleInteval<T> blahblah FIXME FIXME

	public CachedRandomAccessibleInterval(final RandomAccessibleInterval<I> input,
		final LazyExecutionBranch<RandomAccessibleInterval<I>, RandomAccessibleInterval<O>> branch)
	{
		super(input, branch);
	}
}
