
package experimental.tiling.view;

import net.imglib2.RandomAccess;
import net.imglib2.RandomAccessibleInterval;

import experimental.tiling.TilingSchema;

public class TilingView<T> extends TiledView<T> {

	protected final TilingSchema<RandomAccessibleInterval<T>> schema;

	public TilingView(final RandomAccessibleInterval<T> source, final TilingSchema<RandomAccessibleInterval<T>> schema) {
		super(source, schema.getTilesPerDim());

		assert size == schema.getNumTiles();
		assert experimental.tiling.misc.Util.equals(tileSize, schema.getDefaultTileSize());

		this.schema = schema;
	}

	// -- --

	@Override
	public RandomAccess<RandomAccessibleInterval<T>> randomAccess() {
		return new TilingRandomAccess<>(this, source, schema);
	}
}
