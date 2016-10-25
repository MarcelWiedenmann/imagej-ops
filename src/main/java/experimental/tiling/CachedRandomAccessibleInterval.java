
package experimental.tiling;

import net.imglib2.AbstractInterval;
import net.imglib2.Interval;
import net.imglib2.Point;
import net.imglib2.RandomAccess;
import net.imglib2.RandomAccessibleInterval;

import experimental.compgraph.interfaces.ComputationBranch;

// TODO: Caching

public class CachedRandomAccessibleInterval<I, O> extends AbstractInterval implements RandomAccessibleInterval<O> {

	private final RandomAccessibleInterval<I> source;
	private final ComputationBranch<I, O> branch;

	public CachedRandomAccessibleInterval(final RandomAccessibleInterval<I> source,
		final ComputationBranch<I, O> branch)
	{
		super(source);
		this.source = source;
		this.branch = branch;
	}

	@Override
	public RandomAccess<O> randomAccess() {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public RandomAccess<O> randomAccess(final Interval interval) {
		return randomAccess();
	}

	public static class CachedRandomAccess<I, O> extends Point implements RandomAccess<O> {

		@Override
		public O get() {
			throw new UnsupportedOperationException("Not yet implemented");
		}

		@Override
		public CachedRandomAccess<I, O> copy() {
			throw new UnsupportedOperationException("Not yet implemented");
		}

		@Override
		public CachedRandomAccess<I, O> copyRandomAccess() {
			return copy();
		}
	}
}
