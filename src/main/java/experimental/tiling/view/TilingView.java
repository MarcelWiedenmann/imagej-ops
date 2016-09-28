
package experimental.tiling.view;

import net.imglib2.RandomAccess;
import net.imglib2.RandomAccessibleInterval;

import experimental.tiling.TilingSchema;

public class TilingView<T> extends TiledView<T, RandomAccessibleInterval<T>, RandomAccessibleInterval<T>> {

	protected final TilingSchema<T> schema;

	public TilingView(final RandomAccessibleInterval<T> source, final TilingSchema<T> schema) {
		super(source, schema.getTilesPerDim());
		this.schema = schema;

		assert size == schema.getNumTiles();
		assert experimental.tiling.misc.Util.equals(tileSize, schema.getDefaultTileSize());
	}

	// -- --

	@Override
	public RandomAccess<RandomAccessibleInterval<T>> randomAccess() {
		return new TilingRandomAccess<>(this, source, schema);
	}
}
