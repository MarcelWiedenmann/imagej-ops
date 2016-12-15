package experimental.compgraph.tiling;

import net.imglib2.AbstractInterval;
import net.imglib2.Interval;
import net.imglib2.RandomAccess;
import net.imglib2.RandomAccessible;
import net.imglib2.util.IntervalIndexer;

import experimental.compgraph.request.Tile;

public class TilingMask<T> extends AbstractInterval implements RandomAccessible<T>, Iterable<Tile> {

	long[] gridDims;

	long[] tileDims;

	public TilingMask(int n) {
		super(n);
	}

	@Override
	public RandomAccess<T> randomAccess() {
		return null;
	}

	@Override
	public RandomAccess<T> randomAccess(Interval interval) {
		return null;
	}

	public TileInfo[] request(final Tile tile) {
		return request(tile, 0);
	}

	public TileInfo[] request(final Tile tile, final long[] span) {

		long numElements = 1;

		final long[] tmpSpans = new long[span.length];
		final long[] subGridDims = new long[span.length];

		for (int d = 0; d < tileDims.length; d++) {
			if (span[d] != 0) {
				tmpSpans[d] = ((span[d] / tileDims[d]) + 1);
				subGridDims[d] = tmpSpans[d] * 2 + 1;
				numElements *= subGridDims[d];
			}
		}

		final TileInfo[] infos = new TileInfo[(int) numElements];
		if (numElements == 1) {
			infos[0] = new TileInfo(tile.flatIndex());
		} else {

			final long[] centerPos = new long[span.length];
			final long[] tmp = new long[span.length];
			IntervalIndexer.indexToPosition(tile.flatIndex(), gridDims, centerPos);

			long[] tileMin = new long[tmpSpans.length];
			long[] tileMax = tileDims.clone();
			for (int d = 0; d < tileMax.length; d++) {
				tileMax[d]--;
			}

			// TODO make use of symmetry of grid
			for (int i = 0; i < numElements; i++) {
				if (i == (numElements + 1 / 2)) {
					infos[0] = new TileInfo(tile.flatIndex());
				} else {
					IntervalIndexer.indexToPosition(i, subGridDims, tmp);

					for (int d = 0; d < tmp.length; d++) {
						if (tmp[d] == 0 || tmp[d] == subGridDims[d] - 1) {
							long[] global = tmp.clone();
							for (int dd = 0; dd < global.length; dd++) {
								global[dd] += centerPos[dd];
							}
							final TileInfo info;
							if (tmp[d] != 0) {
								long[] tmpTileMin = tileMin.clone();
								tmpTileMin[d] = tileMax[d] - span[d];
								info = new TileInfo(IntervalIndexer.positionToIndex(global, gridDims), tmpTileMin,
										tileMax);
							} else {
								long[] tmpTileMax = tileMax.clone();
								tmpTileMax[d] = span[d];
								info = new TileInfo(IntervalIndexer.positionToIndex(global, gridDims), tileMin,
										tmpTileMax);
							}

							if (infos[i] != null) {
								infos[i].merge(info);
							} else {
								infos[i] = info;
							}
						} else {
							// TODO
							infos[i] = new TileInfo(i);
						}
					}
				}
			}
		}
	}

	private static final class TileInfo {

		// TODO introduce more funny type-patterns;-).
		final byte type;

		final long position;

		final long[] min, max;

		public TileInfo(long position) {
			this(position, null, null);
		}

		public void merge(TileInfo tileInfo) {
			// TODO Auto-generated method stub

		}

		public TileInfo(long position, final long[] min, final long[] max) {
			this.position = position;
			if (min == null || max == null) {
				type = 0;
				this.min = null;
				this.max = null;
			} else {
				this.min = min;
				this.max = max;
				type = 1;
			}
		}
	}
}
