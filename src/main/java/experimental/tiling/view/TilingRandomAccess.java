
package experimental.tiling.view;

import net.imglib2.FinalInterval;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.view.Views;

import experimental.tiling.TilingSchema;

public class TilingRandomAccess<T> extends TiledRandomAccess<T> {

	protected final TilingSchema<RandomAccessibleInterval<T>> schema;

	public TilingRandomAccess(final TilingView<T> view, final RandomAccessibleInterval<T> source,
		final TilingSchema<RandomAccessibleInterval<T>> schema)
	{
		super(view, source, schema.getDefaultTileSize());
		this.schema = schema;
	}

	protected TilingRandomAccess(final TilingRandomAccess<T> randomAccess) {
		super(randomAccess);
		this.schema = randomAccess.schema;
	}

	// -- --

	@Override
	public RandomAccessibleInterval<T> get() {
		final long[] min = new long[n];
		final long[] max = new long[n];
		for (int d = 0; d < n; d++) {
			min[d] = position[d] * tileSize.dimension(d);
			max[d] = min[d] + tileSize.dimension(d) - 1;
			schema.getStrategy().transform(min, max, position, d);
		}
		return Views.interval(source, new FinalInterval(min, max));
	}
}
