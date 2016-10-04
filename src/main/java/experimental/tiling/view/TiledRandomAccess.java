
package experimental.tiling.view;

import net.imglib2.Dimensions;
import net.imglib2.FinalInterval;
import net.imglib2.Point;
import net.imglib2.RandomAccess;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.Sampler;
import net.imglib2.view.Views;

public class TiledRandomAccess<T> extends Point implements RandomAccess<RandomAccessibleInterval<T>> {

	protected final TiledView<T> view;
	protected final RandomAccessibleInterval<T> source;
	protected final Dimensions tileSize;

	public TiledRandomAccess(final TiledView<T> view, final RandomAccessibleInterval<T> source,
		final Dimensions tileSize)
	{
		super(source.numDimensions());

		assert tileSize.numDimensions() == n;

		this.view = view;
		this.source = source;
		this.tileSize = tileSize;
	}

	protected TiledRandomAccess(final TiledRandomAccess<T> randomAccess) {
		super(randomAccess.position, true);
		view = randomAccess.view;
		source = randomAccess.source;
		tileSize = randomAccess.tileSize;
	}

	@Override
	public RandomAccessibleInterval<T> get() {
		final long[] min = new long[n];
		final long[] max = new long[n];
		for (int d = 0; d < n; d++) {
			min[d] = position[d] * tileSize.dimension(d);
			max[d] = min[d] + tileSize.dimension(d) - 1;
		}
		return Views.interval(source, new FinalInterval(min, max));
	}

	@Override
	public Sampler<RandomAccessibleInterval<T>> copy() {
		return copyRandomAccess();
	}

	@Override
	public RandomAccess<RandomAccessibleInterval<T>> copyRandomAccess() {
		return new TiledRandomAccess<>(this);
	}
}
