
package experimental.tiling.view;

import net.imglib2.Dimensions;
import net.imglib2.FinalInterval;
import net.imglib2.Point;
import net.imglib2.RandomAccess;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.Sampler;
import net.imglib2.view.Views;

public class TilesRandomAccess<T, I extends RandomAccessibleInterval<T>, O extends RandomAccessibleInterval<T>>
	extends Point implements RandomAccess<O>
{

	protected final TilesView<T, I, O> view;
	protected final I source;
	protected final Dimensions tileSize;

	public TilesRandomAccess(final TilesView<T, I, O> view, final I source, final Dimensions tileSize) {
		super(source.numDimensions());

		assert tileSize.numDimensions() == n;

		this.view = view;
		this.source = source;
		this.tileSize = tileSize;
	}

	protected TilesRandomAccess(final TilesRandomAccess<T, I, O> randomAccess) {
		super(randomAccess.position, true);
		view = randomAccess.view;
		source = randomAccess.source;
		tileSize = randomAccess.tileSize;
	}

	@Override
	public O get() {
		final long[] min = new long[n];
		final long[] max = new long[n];
		for (int d = 0; d < n; d++) {
			min[d] = position[d] * tileSize.dimension(d);
			max[d] = min[d] + tileSize.dimension(d) - 1;
		}
		return (O) Views.interval(source, new FinalInterval(min, max));
	}

	@Override
	public Sampler<O> copy() {
		return copyRandomAccess();
	}

	@Override
	public RandomAccess<O> copyRandomAccess() {
		return new TilesRandomAccess<>(this);
	}
}
