
package experimental.tiling.view;

import java.util.Arrays;
import java.util.List;

import net.imglib2.AbstractCursor;
import net.imglib2.AbstractInterval;
import net.imglib2.FlatIterationOrder;
import net.imglib2.Interval;
import net.imglib2.IterableInterval;
import net.imglib2.Point;
import net.imglib2.RandomAccess;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.View;
import net.imglib2.util.IntervalIndexer;

public class ArrangedView<T> extends AbstractInterval implements RandomAccessibleInterval<RandomAccessibleInterval<T>>,
	IterableInterval<RandomAccessibleInterval<T>>, View
{

	private final RandomAccessibleInterval<T>[] source;
	private final long[] dimensions;

	public ArrangedView(final List<RandomAccessibleInterval<T>> source, final long... dimensions) {
		super(dimensions);
		this.source = source.toArray(new RandomAccessibleInterval[source.size()]);
		this.dimensions = dimensions;
	}

	public List<RandomAccessibleInterval<T>> getSource() {
		return Arrays.asList(source);
	}

	@Override
	public ArrangedViewRandomAccess<T> randomAccess() {
		return new ArrangedViewRandomAccess<T>(source, dimensions);
	}

	@Override
	public ArrangedViewRandomAccess<T> randomAccess(final Interval interval) {
		return randomAccess();
	}

	@Override
	public long size() {
		return source.length;
	}

	@Override
	public RandomAccessibleInterval<T> firstElement() {
		return source[0];
	}

	@Override
	public Object iterationOrder() {
		return new FlatIterationOrder(this);
	}

	@Override
	public ArrangedViewCursor<T> iterator() {
		return cursor();
	}

	@Override
	public ArrangedViewCursor<T> cursor() {
		return new ArrangedViewCursor<T>(source, dimensions);
	}

	@Override
	public ArrangedViewCursor<T> localizingCursor() {
		return cursor();
	}

	public static class ArrangedViewRandomAccess<T> extends Point implements RandomAccess<RandomAccessibleInterval<T>> {

		private final RandomAccessibleInterval<T>[] source;
		private final long[] dimensions;

		public ArrangedViewRandomAccess(final RandomAccessibleInterval<T>[] source, final long[] dimensions) {
			super(dimensions.length);
			this.source = source;
			this.dimensions = dimensions;
		}

		private ArrangedViewRandomAccess(final ArrangedViewRandomAccess<T> ra) {
			super(ra.position, true);
			source = ra.source;
			dimensions = ra.dimensions;
		}

		@Override
		public RandomAccessibleInterval<T> get() {
			final int i = (int) IntervalIndexer.positionToIndex(position, dimensions);
			return source[i];
		}

		@Override
		public ArrangedViewRandomAccess<T> copy() {
			return new ArrangedViewRandomAccess<>(this);
		}

		@Override
		public ArrangedViewRandomAccess<T> copyRandomAccess() {
			return copy();
		}
	}

	public static class ArrangedViewCursor<T> extends AbstractCursor<RandomAccessibleInterval<T>> {

		private final RandomAccessibleInterval<T>[] source;
		private final long[] dimensions;
		private final int maxIndex;
		private int i;

		public ArrangedViewCursor(final RandomAccessibleInterval<T>[] source, final long[] dimensions) {
			super(dimensions.length);
			this.source = source;
			this.dimensions = dimensions;
			this.maxIndex = source.length - 1;
			reset();
		}

		private ArrangedViewCursor(final ArrangedViewCursor<T> cursor) {
			super(cursor.n);
			this.source = cursor.source;
			this.dimensions = cursor.dimensions;
			this.maxIndex = cursor.maxIndex;
			this.i = cursor.i;
		}

		@Override
		public RandomAccessibleInterval<T> get() {
			return source[i];
		}

		@Override
		public void fwd() {
			++i;
		}

		@Override
		public void jumpFwd(final long steps) {
			i += steps;
		}

		@Override
		public void reset() {
			i = -1;
		}

		@Override
		public boolean hasNext() {
			return i < maxIndex;
		}

		@Override
		public void localize(final long[] position) {
			IntervalIndexer.indexToPosition(i, dimensions, position);
		}

		@Override
		public long getLongPosition(final int d) {
			return IntervalIndexer.indexToPosition(i, dimensions, d);
		}

		@Override
		public ArrangedViewCursor<T> copy() {
			return new ArrangedViewCursor<>(this);
		}

		@Override
		public ArrangedViewCursor<T> copyCursor() {
			return copy();
		}
	}
}
