
package experimental.tiling;

import net.imglib2.Dimensions;
import net.imglib2.Interval;
import net.imglib2.RandomAccessibleInterval;

import experimental.tiling.view.ImageAsTilingView;

public class Tiling<I, O> {

	private final RandomAccessibleInterval<I> in;
	private final long numTiles;
	private final Dimensions tilesPerDim;
	private final Dimensions tileSize;
	private final TilingStrategy strategy;

	private LazyTile<I, O> root;

	public Tiling(final RandomAccessibleInterval<I> in, final long numTiles, final Dimensions tilesPerDim,
		final Dimensions tileSize, final TilingStrategy strategy)
	{
		this.in = in;
		this.numTiles = numTiles;
		this.tilesPerDim = tilesPerDim;
		this.tileSize = tileSize;
		this.strategy = strategy.copy(this);
	}

	protected Tiling(final Tiling<I, O> tiling) {
		in = tiling.in;
		numTiles = tiling.numTiles;
		tilesPerDim = tiling.tilesPerDim;
		tileSize = tiling.tileSize;
		strategy = tiling.strategy;
	}

	public int numDimensions() {
		return in.numDimensions();
	}

	public Interval getInput() {
		return in;
	}

	public Dimensions getTilesPerDim() {
		return tilesPerDim;
	}

	public long getNumTiles() {
		return numTiles;
	}

	public Dimensions getDefaultTileSize() {
		return tileSize;
	}

	public TilingStrategy getStrategy() {
		return strategy;
	}

	public ImageAsTilingView view() {

	}
}
