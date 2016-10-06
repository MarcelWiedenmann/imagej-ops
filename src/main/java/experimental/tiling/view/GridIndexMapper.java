
package experimental.tiling.view;

import net.imglib2.Dimensions;
import net.imglib2.Interval;

public final class GridIndexMapper {

	private final int n;
	private final Interval interval;
	private final Dimensions tileSize;
	private final long[] tilesPerDim;

	public GridIndexMapper(final Interval interval, final Dimensions tileSize, final Dimensions tilesPerDim,
		final int[] mappingOrder)
	{
		n = interval.numDimensions();

		assert tileSize.numDimensions() == n;
		assert tilesPerDim.numDimensions() == n;
		assert mappingOrder.length == n;

		this.interval = interval;
		this.tileSize = tileSize;
		this.tilesPerDim = new long[n];
		tilesPerDim.dimensions(this.tilesPerDim);
	}

	public Interval getInterval() {
		return interval;
	}

	public long getFlatTileIndex(final long[] index) {
		assert index.length == n;

		long flatIndex = 0;
		for (int k = n - 1; k >= 0; k--) {
			if (index[mappingOrder[k]] < 0 || tilesPerDim[mappingOrder[k]] <= index[mappingOrder[k]]) {
				throw new IndexOutOfBoundsException("'index' exceeds 'tilesPerDim'");
			}
			flatIndex = flatIndex * tilesPerDim[mappingOrder[k]] + index[mappingOrder[k]];
		}
		return flatIndex;
	}

	public void getTileIndex(long flatIndex, final long[] index) {
		assert index.length == n;

		for (final int d : mappingOrder) {
			final long i = flatIndex / tilesPerDim[d];
			index[d] = flatIndex - i * tilesPerDim[d];
			if (index[d] < 0 || tilesPerDim[d] <= index[d]) {
				throw new IndexOutOfBoundsException("'index' exceeds 'tilesPerDim'");
			}
			flatIndex = i;
		}
	}

	public void getTileIndexAndLocalPosition(final long[] position, final long[] tileIndex,
		final long[] localPosition /* , final boolean fillBorder */)
	{
		assert position.length == n;
		assert tileIndex.length == n;
		assert localPosition.length == n;

		for (int d = 0; d < n; d++) {
			if (position[d] < interval.min(d) || interval.max(d) < position[d]) {
				throw new IndexOutOfBoundsException("'position' exceeds defined interval");
			}
			tileIndex[d] = position[d] / tileSize.dimension(d);
			localPosition[d] = position[d] % tileSize.dimension(d);
			if (tileIndex[d] >= tilesPerDim[d]) {
				// Enlarge border tile to fill dimension until position is reached?
				// if (fillBorder) {
				tileIndex[d]--;
				localPosition[d] += tileSize.dimension(d);
				// }
				// else {
				// throw new IndexOutOfBoundsException("'position' maps to an index that exceeds 'tilesPerDim'");
				// }
			}
		}
	}

	public long getFlatTileIndexAndLocalPosition(final long[] position,
		final long[] localPosition /* , final boolean fillBorder */)
	{
		// TODO: Implement more performant mapping by merging the D-loops of the two other methods.
		final long[] tileIndex = new long[position.length];
		getTileIndexAndLocalPosition(position, tileIndex, localPosition /* , fillBorder */);
		return getFlatTileIndex(tileIndex);
	}
}
