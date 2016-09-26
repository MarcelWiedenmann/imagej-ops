
package experimental.tiling.view;

import net.imglib2.FinalInterval;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.view.Views;

import experimental.tiling.Tiling;

public class ImageAsTilingRandomAccess<T> extends
	TilesRandomAccess<T, RandomAccessibleInterval<T>, RandomAccessibleInterval<T>>
{

	protected final Tiling description;

	public ImageAsTilingRandomAccess(final ImageAsTilingView<T> view, final RandomAccessibleInterval<T> source,
		final Tiling description)
	{
		super(view, source, description.getDefaultTileSize());
		this.description = description;
	}

	protected ImageAsTilingRandomAccess(final ImageAsTilingRandomAccess<T> randomAccess) {
		super(randomAccess);
		this.description = randomAccess.description;
	}

	// -- --
	@Override
	public RandomAccessibleInterval<T> get() {
		final long[] min = new long[n];
		final long[] max = new long[n];
		for (int d = 0; d < n; d++) {
			min[d] = position[d] * tileSize.dimension(d);
			max[d] = min[d] + tileSize.dimension(d) - 1;
			description.getStrategy().transform(min, max, position, d);
		}
		return Views.interval(source, new FinalInterval(min, max));
	}
}
