
package experimental.tiling.execution;

import net.imglib2.Cursor;
import net.imglib2.converter.AbstractConvertedCursor;

public class LazyCursor<I, O> extends AbstractConvertedCursor<I, LazyExecution<I, O>> {

	protected LazyExecutionBranch<I, O> branch;

	public LazyCursor(final Cursor<I> source, final LazyExecutionBranch<I, O> branch) {
		super(source);
		this.branch = branch;
	}

	private LazyCursor(final LazyCursor<I, O> cursor) {
		super(cursor.source.copyCursor());
		this.branch = cursor.branch;
	}

	// -- Sampler --

	@Override
	public LazyExecution<I, O> get() {
		final I input = source.get();
		return new LazyExecution<I, O>(input, branch);
	}

	@Override
	public LazyCursor<I, O> copy() {
		return new LazyCursor<I, O>(this);
	}
}
