
package experimental.tiling;

import net.imglib2.Dimensions;
import net.imglib2.RandomAccessibleInterval;

public class TilingSchema<I> {

	private final RandomAccessibleInterval<I> in;
	private final long numTiles;
	private final Dimensions tilesPerDim;
	private final Dimensions tileSize;
	private final TilingStrategy strategy;

	public TilingSchema(final RandomAccessibleInterval<I> in, final long numTiles, final Dimensions tilesPerDim,
		final Dimensions tileSize, final TilingStrategy strategy)
	{
		this.in = in;
		this.numTiles = numTiles;
		this.tilesPerDim = tilesPerDim;
		this.tileSize = tileSize;
		this.strategy = strategy; // FIXME!
	}

	protected TilingSchema(final TilingSchema<I> tiling) {
		in = tiling.in;
		numTiles = tiling.numTiles;
		tilesPerDim = tiling.tilesPerDim;
		tileSize = tiling.tileSize;
		strategy = tiling.strategy;
	}

	public RandomAccessibleInterval<I> getInput() {
		return in;
	}

	public int numDimensions() {
		return in.numDimensions();
	}

	public long getNumTiles() {
		return numTiles;
	}

	public Dimensions getTilesPerDim() {
		return tilesPerDim;
	}

	public Dimensions getDefaultTileSize() {
		return tileSize;
	}

	public TilingStrategy getStrategy() {
		return strategy;
	}
}
