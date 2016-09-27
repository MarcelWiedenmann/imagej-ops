
package experimental.tiling.view;

import net.imglib2.RandomAccess;
import net.imglib2.RandomAccessibleInterval;

import experimental.tiling.Tiling;

public class TilingView<T> extends
	TiledView<T, RandomAccessibleInterval<T>, RandomAccessibleInterval<T>>
{

	protected final Tiling description;

	public TilingView(final RandomAccessibleInterval<T> source, final Tiling description) {
		super(source, description.getTilesPerDim());
		this.description = description;

		assert size == description.getNumTiles();
		assert experimental.tiling.misc.Util.equals(tileSize, description.getDefaultTileSize());
	}

	// -- --

	@Override
	public RandomAccess<RandomAccessibleInterval<T>> randomAccess() {
		return new TilingRandomAccess<>(this, source, description);
	}
}
