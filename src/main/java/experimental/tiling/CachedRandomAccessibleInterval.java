
package experimental.tiling;

import net.imglib2.RandomAccessibleInterval;

import mapreduce.LazyExecution;
import mapreduce.LazyExecutionBranch;

public class CachedRandomAccessibleInterval<I, O> extends
	LazyExecution<RandomAccessibleInterval<I>, RandomAccessibleInterval<O>>
{

	public CachedRandomAccessibleInterval(final RandomAccessibleInterval<I> input,
		final LazyExecutionBranch<RandomAccessibleInterval<I>, RandomAccessibleInterval<O>> branch)
	{
		super(input, branch);
	}
}
