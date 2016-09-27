
package experimental.tiling;

import net.imglib2.Dimensions;
import net.imglib2.Interval;
import net.imglib2.RandomAccessibleInterval;

import experimental.tiling.view.TilingView;

public class Tiling<I, O> {

	private final RandomAccessibleInterval<I> in;
	private final long numTiles;
	private final Dimensions tilesPerDim;
	private final Dimensions tileSize;
	private final TilingStrategy strategy;

	public Tiling(final RandomAccessibleInterval<I> in, final long numTiles, final Dimensions tilesPerDim,
		final Dimensions tileSize, final TilingStrategy strategy)
	{
		this.in = in;
		this.numTiles = numTiles;
		this.tilesPerDim = tilesPerDim;
		this.tileSize = tileSize;
		this.strategy = strategy;
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

	public TilingView<I> view() {
		final TilingView<I> view = new TilingView<>(in, this);
		return view;
	}
}
