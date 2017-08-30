
package experimental.compgraph.execution;

import java.util.Spliterator;
import java.util.function.Consumer;

import net.imglib2.Cursor;
import net.imglib2.IterableInterval;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.view.IntervalView;
import net.imglib2.view.Views;

public class TilingSpliterator<T> implements Spliterator<RandomAccessibleInterval<T>> {

	private RandomAccessibleInterval<? extends RandomAccessibleInterval<T>> source;
	private IterableInterval<? extends RandomAccessibleInterval<T>> sourceIterable;
	private Cursor<? extends RandomAccessibleInterval<T>> sourceCursor;

	public RandomAccessibleInterval<? extends RandomAccessibleInterval<T>> getSource() {
		return source;
	}

	public TilingSpliterator(final RandomAccessibleInterval<? extends RandomAccessibleInterval<T>> source) {
		this.source = source;
	}

	// -- Spliterator --

	@Override
	public boolean tryAdvance(final Consumer<? super RandomAccessibleInterval<T>> action) {
		initialize();
		return tryAdvanceInternal(action);
	}

	@Override
	public void forEachRemaining(final Consumer<? super RandomAccessibleInterval<T>> action) {
		initialize();
		do {}
		while (tryAdvanceInternal(action));
	}

	// TODO: we probably need to support another split approach than just purely binary forking (something like
	// trySplit(numPartitions))
	@Override
	public TilingSpliterator<T> trySplit() {
		final int n = source.numDimensions();
		final long[] min = new long[n];
		final long[] max = new long[n];
		int splitDim = -1;
		long splitDimSize = -1;
		boolean splitIsEven = false;
		for (int d = n - 1; d >= 0; d--) {
			min[d] = source.min(d);
			max[d] = source.max(d);
			// NB: We look for the highest dimension that is evenly divisible.
			if (splitIsEven) {
				continue;
			}
			final long currDimSize = source.dimension(d);
			final boolean currIsEven = currDimSize % 2 == 0;
			if (currIsEven) {
				splitDim = d;
				splitDimSize = currDimSize;
				splitIsEven = true;
			}
		}
		// TODO: enhance split point search to find a "best effort" split point in case we have no even split (i.e. find a
		// split point that minimizes the difference between the number of elements of the split parts; this basically is a
		// generalization of the current strategy as an even split minimizes this difference trivially).
		if (!splitIsEven) {
			splitDim = n - 1;
			splitDimSize = source.dimension(splitDim);
		}
		final long splitPoint = splitDimSize / 2;
		final long tempMax = max[splitDim];
		max[splitDim] -= splitPoint + (splitIsEven ? 0 : 1);
		final IntervalView<? extends RandomAccessibleInterval<T>> firstInterval = Views.interval(source, min, max);
		updateSource(firstInterval);
		// NB: We use our min/max-arrays for both views. This works because we know implementation details. Beware of
		// implementation changes!
		max[splitDim] = tempMax;
		min[splitDim] += splitPoint;
		final IntervalView<? extends RandomAccessibleInterval<T>> secondInterval = Views.interval(source, min, max);
		return new TilingSpliterator<>(secondInterval);
	}

	@Override
	public long estimateSize() {
		initialize();
		return sourceIterable.size();
	}

	@Override
	public int characteristics() {
		return ORDERED | SIZED | SUBSIZED | IMMUTABLE;
	}

	// -- Private Methods --

	private void initialize() {
		if (sourceCursor == null) {
			if (sourceIterable == null) {
				sourceIterable = Views.iterable(source);
			}
			sourceCursor = sourceIterable.cursor();
		}
	}

	private void updateSource(final RandomAccessibleInterval<? extends RandomAccessibleInterval<T>> firstInterval) {
		source = firstInterval;
		sourceIterable = null;
		sourceCursor = null;
	}

	private boolean tryAdvanceInternal(final Consumer<? super RandomAccessibleInterval<T>> action) {
		if (sourceCursor.hasNext()) {
			action.accept(sourceCursor.next());
			return true;
		}
		return false;
	}
}
