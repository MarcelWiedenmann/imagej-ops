
package experimental.tiling;

import net.imglib2.Dimensions;
import net.imglib2.FinalDimensions;
import net.imglib2.RandomAccessibleInterval;

public class TilingConfiguration {

	public enum TilingType {
			FixedTileSize, FixedTilesPerDimension
	}

	private final Dimensions dimensions;
	private TilingType type = TilingType.FixedTileSize;
	private TilingStrategy strategy = new DefaultTilingStrategy();

	public TilingConfiguration(final long... dimensions) {
		this(new FinalDimensions(dimensions));
	}

	public TilingConfiguration(final Dimensions dimensions) {
		this.dimensions = dimensions;
	}

	public Dimensions getDimensions() {
		return dimensions;
	}

	public TilingType getTilingType() {
		return type;
	}

	public void setTilingType(final TilingType type) {
		this.type = type;
	}

	public TilingStrategy getStrategy() {
		return strategy;
	}

	public void setStrategy(final TilingStrategy strategy) {
		this.strategy = strategy;
	}

	public <I extends RandomAccessibleInterval<?>> TilingSchema<I> generateSchema(final I in) {
		long numTiles;
		Dimensions tilesPerDim;
		Dimensions tileSize;
		if (type == TilingType.FixedTileSize) {
			tileSize = dimensions;
			numTiles = 1;
			final long[] tPerDim = new long[in.numDimensions()];
			for (int d = 0; d < in.numDimensions(); d++) {
				tPerDim[d] = in.dimension(d) % tileSize.dimension(d) == 0
					? in.dimension(d) / tileSize.dimension(d)
					: in.dimension(d) / tileSize.dimension(d) + 1;
				numTiles *= tPerDim[d];
			}
			tilesPerDim = FinalDimensions.wrap(tPerDim);
		}
		else if (type == TilingType.FixedTilesPerDimension) {
			tilesPerDim = dimensions;
			numTiles = 1;
			final long[] tSize = new long[in.numDimensions()];
			for (int d = 0; d < in.numDimensions(); d++) {
				tSize[d] = in.dimension(d) / tilesPerDim.dimension(d);
				numTiles *= tilesPerDim.dimension(d);
			}
			tileSize = FinalDimensions.wrap(tSize);
		}
		else throw new RuntimeException("Tiling type not supported.");
		return new TilingSchema<I>(in, numTiles, tilesPerDim, tileSize, strategy);
	}
}
