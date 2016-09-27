
package experimental.tiling;

import java.util.ArrayList;
import java.util.List;

import net.imagej.ops.Op;
import net.imglib2.Dimensions;
import net.imglib2.FinalDimensions;
import net.imglib2.FinalInterval;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.view.IntervalView;
import net.imglib2.view.Views;

import experimental.tiling.misc.Util;
import experimental.tiling.ops.interfaces.TilableOp;
import experimental.tiling.view.TileIndexMapper;

// TODO: Convert TilinStrategy to op (and use as 1st and last in op chain)?
public class TilingStrategy {

	private Dimensions overlap = Util.ZeroDimensions;
	@SuppressWarnings("rawtypes")
	private Tiling tiling = null;

	public TilingStrategy() {}

	private <I, O> TilingStrategy(final Dimensions overlap, final Tiling<I, O> tiling) {
		assert overlap.numDimensions() >= tiling.numDimensions();

		// FIXME: What if the overlap of e.g. the 2nd tile is bigger than the neighboring 1st tile? --> Out of image bounds!
		// Restrict overlap!

		this.overlap = overlap;
		this.tiling = tiling;
	}

	public Dimensions getOverlap() {
		return overlap;
	}

	public int numDimensions() {
		return overlap.numDimensions();
	}

	public void transform(final long[] min, final long[] max, final long[] index, final int d) {
		assert overlap != null && tiling != null;

		// Only transform inner boundaries.
		if (index[d] > 0) {
			min[d] -= overlap.dimension(d);
		}
		if (index[d] < tiling.getTilesPerDim().dimension(d) - 1) {
			max[d] += overlap.dimension(d);
		}
		// Enlarge or shrink last tile in dimension to fit original image.
		else {
			max[d] = tiling.getInput().max(d);
		}
	}

	public <T> List<RandomAccessibleInterval<T>> transformBack(final List<RandomAccessibleInterval<T>> tiles,
		final TileIndexMapper mapper)
	{
		final ArrayList<RandomAccessibleInterval<T>> transformedTiles = new ArrayList<>(tiles.size());
		final Dimensions tilesPerDim = tiling.getTilesPerDim();
		for (int i = 0; i < tiles.size(); i++) {
			final RandomAccessibleInterval<T> tile = tiles.get(i);
			final long[] min = new long[tile.numDimensions()];
			final long[] max = new long[tile.numDimensions()];
			tile.min(min);
			tile.max(max);
			final long[] tileIndex = new long[tile.numDimensions()];
			mapper.getTileIndex(i, tileIndex);
			for (int d = 0; d < tile.numDimensions(); d++) {
				// Border checks
				if (tileIndex[d] > 0) {
					min[d] += overlap.dimension(d);
				}
				if (tileIndex[d] < tilesPerDim.dimension(d) - 1) {
					max[d] -= overlap.dimension(d);
				}
			}
			final FinalInterval innerTile = new FinalInterval(min, max);
			final IntervalView<T> innerTileView = Views.interval(tile, innerTile);
			transformedTiles.add(innerTileView);
		}
		return transformedTiles;
	}

	public <I, O> TilingStrategy copy(final Tiling<I, O> tiling, final Op... ops) {
		int maxNumDimensions = tiling.numDimensions();
		final ArrayList<Dimensions> opOverlaps = new ArrayList<Dimensions>(ops.length);
		for (final Op op : ops) {
			if (op instanceof TilableOp) {
				final Dimensions opOverlap = ((TilableOp) op).getOverlap();
				opOverlaps.add(opOverlap);
				if (opOverlap.numDimensions() > maxNumDimensions) maxNumDimensions = opOverlap.numDimensions();
			}
		}
		// Default: Add up overlaps to enable image filtering or other neighborhood-dependent operations.
		final long[] combinedOverlap = new long[maxNumDimensions];
		for (final Dimensions overlap : opOverlaps) {
			for (int d = 0; d < overlap.numDimensions(); d++) {
				combinedOverlap[d] += overlap.dimension(d);
			}
		}
		return new TilingStrategy(FinalDimensions.wrap(combinedOverlap), tiling);
	}
}
