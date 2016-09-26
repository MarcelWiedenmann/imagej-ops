
package experimental.tiling.view;

import net.imglib2.AbstractInterval;
import net.imglib2.Cursor;
import net.imglib2.Dimensions;
import net.imglib2.FinalDimensions;
import net.imglib2.FlatIterationOrder;
import net.imglib2.Interval;
import net.imglib2.IterableInterval;
import net.imglib2.RandomAccess;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.View;
import net.imglib2.view.RandomAccessibleIntervalCursor;

import experimental.tiling.misc.Util;

public class TilesView<T, I extends RandomAccessibleInterval<T>, O extends RandomAccessibleInterval<T>> extends
	AbstractInterval implements RandomAccessibleInterval<O>, IterableInterval<O>, View
{

	protected final I source;
	protected final Dimensions tilesPerDim;
	protected final long size;
	protected final Dimensions tileSize;
	private RandomAccessibleIntervalCursor<O> cursor;

	public TilesView(final I source, final long[] tilesPerDim) {
		this(source, new FinalDimensions(tilesPerDim));
	}

	public TilesView(final I source, final Dimensions tilesPerDim) {
		super(tilesPerDim);

		assert source.numDimensions() == n;
		assert Util.isZeroMin(source);

		this.source = source;
		this.tilesPerDim = tilesPerDim;
		long sz = 1;
		final long[] tileSz = new long[n];
		for (int d = 0; d < n; d++) {
			sz *= tilesPerDim.dimension(d);
			tileSz[d] = source.dimension(d) / tilesPerDim.dimension(d);
		}
		this.size = sz;
		this.tileSize = FinalDimensions.wrap(tileSz);
	}

	// -- RandomAccessibleInterval --

	@Override
	public RandomAccess<O> randomAccess() {
		return new TilesRandomAccess<>(this, source, tileSize);
	}

	@Override
	public RandomAccess<O> randomAccess(final Interval interval) {
		return randomAccess();
	}

	// -- IterableInterval --

	@Override
	public long size() {
		return size;
	}

	@Override
	public O firstElement() {
		if (cursor == null) {
			cursor = new RandomAccessibleIntervalCursor<>(this);
		}
		else {
			cursor.reset();
		}
		return cursor.next();
	}

	@Override
	public Object iterationOrder() {
		return new FlatIterationOrder(this);
	}

	@Override
	public Cursor<O> iterator() {
		return cursor();
	}

	@Override
	public Cursor<O> cursor() {
		return new RandomAccessibleIntervalCursor<>(this);
	}

	@Override
	public Cursor<O> localizingCursor() {
		return cursor();
	}
}
