
package experimental.tiling;

import net.imglib2.Cursor;
import net.imglib2.RandomAccessibleInterval;

import experimental.tiling.execution.LazyCursor;
import experimental.tiling.execution.LazyExecutionBranch;

public class TilingCursor<I, O> extends LazyCursor<RandomAccessibleInterval<I>, RandomAccessibleInterval<O>> {

	public TilingCursor(final Cursor<RandomAccessibleInterval<I>> source,
		final LazyExecutionBranch<RandomAccessibleInterval<I>, RandomAccessibleInterval<O>> branch)
	{
		super(source, branch);
	}

	// -- LazyCursor --

	@Override
	public CachedRandomAccessibleInterval<I, O> get() {
		final RandomAccessibleInterval<I> input = source.get();
		return new CachedRandomAccessibleInterval<I, O>(input, branch);
	}
}
