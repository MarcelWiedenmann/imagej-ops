
package experimental.tiling;

import java.util.ArrayList;
import java.util.Iterator;
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
	private TilingSchema schema = null;

	public TilingStrategy() {}

	private <I> TilingStrategy(final Dimensions overlap, final TilingSchema<I> schema) {
		assert overlap.numDimensions() >= schema.numDimensions();

		// FIXME: What if the overlap of e.g. the 2nd tile is bigger than the neighboring 1st tile? --> Out of image bounds!
		// Restrict overlap!

		this.overlap = overlap;
		this.schema = schema;
	}

	public Dimensions getOverlap() {
		return overlap;
	}

	public int numDimensions() {
		return overlap.numDimensions();
	}

	public void transform(final long[] min, final long[] max, final long[] index, final int d) {
		assert overlap != null && schema != null;

		// Only transform inner boundaries.
		if (index[d] > 0) {
			min[d] -= overlap.dimension(d);
		}
		if (index[d] < schema.getTilesPerDim().dimension(d) - 1) {
			max[d] += overlap.dimension(d);
		}
		// Enlarge or shrink last tile in dimension to fit original image.
		else {
			max[d] = schema.getInput().max(d);
		}
	}

	public <T> List<RandomAccessibleInterval<T>> transformBack(final List<RandomAccessibleInterval<T>> tiles,
		final TileIndexMapper mapper)
	{
		final ArrayList<RandomAccessibleInterval<T>> transformedTiles = new ArrayList<>(tiles.size());
		final Dimensions tilesPerDim = schema.getTilesPerDim();
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

	public <I, O> TilingStrategy copy(final Tiling<I, O> tiling) {
		final TilingSchema<I> schema = tiling.getSchema();
		final Iterator<Op> i = tiling.opIterator();
		int maxNumDimensions = schema.numDimensions();
		final ArrayList<Dimensions> opOverlaps = new ArrayList<Dimensions>();
		while (i.hasNext()) {
			final Op op = i.next();
			if (op instanceof TilableOp) {
				final Dimensions opOverlap = ((TilableOp) op).getOverlap();
				opOverlaps.add(opOverlap);
				if (opOverlap.numDimensions() > maxNumDimensions) maxNumDimensions = opOverlap.numDimensions();
			}
		}
		// Default: Add up overlaps to facilitate image filtering or other neighborhood-dependent operations.
		// TODO: We could put this part in its own method for ease of overriding (as customized overlap handling is one of
		// the main reasons we introduced a tiling strategy class in the first place).
		final long[] combinedOverlap = new long[maxNumDimensions];
		for (final Dimensions overlap : opOverlaps) {
			for (int d = 0; d < overlap.numDimensions(); d++) {
				combinedOverlap[d] += overlap.dimension(d);
			}
		}
		return new TilingStrategy(FinalDimensions.wrap(combinedOverlap), schema);
	}
}
