
package experimental.compgraph.tiling;

import net.imglib2.FinalInterval;
import net.imglib2.Interval;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.view.experimental.TiledView;

import experimental.compgraph.request.DefaultTilesRequest;
import experimental.compgraph.request.TilingRequestable;

public class TiledComputationResultsView<T> extends TiledView<T> {

	private final TilingRequestable<T> supplier;

	public TiledComputationResultsView(final RandomAccessibleInterval<T> source, final long[] blockSize,
		final TilingRequestable<T> supplier)
	{
		super(source, blockSize);
		this.supplier = supplier;
	}

	public TilingRequestable<T> getSupplier() {
		return supplier;
	}

	// -- Tiled View --

	@Override
	public TiledComputationResultsRandomAccess<T> randomAccess() {
		return new TiledComputationResultsRandomAccess<>(getSource(), blockSize, max, supplier);
	}

	@Override
	public TiledComputationResultsRandomAccess<T> randomAccess(final Interval interval) {
		return randomAccess();
	}

	// -- Nested Class --

	public static class TiledComputationResultsRandomAccess<T> extends TiledViewRandomAccess<T> {

		private final TilingRequestable<T> supplier;

		public TiledComputationResultsRandomAccess(final RandomAccessibleInterval<T> source, final long[] blockSize,
			final long[] max, final TilingRequestable<T> supplier)
		{
			super(source, blockSize, max);
			this.supplier = supplier;
		}

		protected TiledComputationResultsRandomAccess(final TiledComputationResultsRandomAccess<T> ra) {
			super(ra);
			this.supplier = ra.supplier;
		}

		// -- TiledViewRandomAccess --

		@Override
		public RandomAccessibleInterval<T> get() {
			for (int d = 0; d < n; ++d) {
				tempMin[d] = position[d] * blockSize[d];
				if (position[d] < max[d]) {
					tempMax[d] = tempMin[d] + blockSize[d] - 1;
				}
				else {
					tempMax[d] = source.max(d);
				}
			}
			final DefaultTilesRequest r = new DefaultTilesRequest(new FinalInterval(tempMin, tempMax));
			return supplier.request(r);
		}

		@Override
		public TiledComputationResultsRandomAccess<T> copy() {
			return new TiledComputationResultsRandomAccess<>(this);
		}

		@Override
		public TiledComputationResultsRandomAccess<T> copyRandomAccess() {
			return copy();
		}
	}
}
