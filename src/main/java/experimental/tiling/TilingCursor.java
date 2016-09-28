
package experimental.tiling;

import net.imglib2.Cursor;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.converter.AbstractConvertedCursor;

import experimental.tiling.execution.LazyExecutionBranch;

public class TilingCursor<I, O> extends
	AbstractConvertedCursor<RandomAccessibleInterval<I>, CachedRandomAccessibleInterval<I, O>>
{

	private LazyExecutionBranch<I, O> branch;

	public TilingCursor(final Cursor<RandomAccessibleInterval<I>> source, final LazyExecutionBranch<I, O> branch) {
		super(source);
		this.branch = branch;
	}

	private TilingCursor(final TilingCursor<I, O> cursor) {
		super(cursor.source.copyCursor());
	}

	// -- Sampler --

	@Override
	public CachedRandomAccessibleInterval<I, O> get() {
		final RandomAccessibleInterval<I> input = source.get();

		return new CachedRandomAccessibleInterval<I, O>(input, branch);
	}

	@Override
	public TilingCursor<I, O> copy() {
		return new TilingCursor<I, O>(this);
	}
}
