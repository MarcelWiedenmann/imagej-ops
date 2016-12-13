package experimental.compgraph.request;

import java.util.HashMap;
import java.util.Map;

import net.imglib2.AbstractInterval;
import net.imglib2.Interval;
import net.imglib2.Point;
import net.imglib2.RandomAccess;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.Sampler;

public class LazyTiling<T> extends AbstractInterval implements RandomAccessibleInterval<T> {

	private final long[] gridDims;
	private final long[] tileDims;

	private LazyTileAccess<T> tilingAccess;

	public LazyTiling(final long[] dims, long[] tileDims, final LazyTileAccess<T> tilingAccess) {
		super(dims);
		gridDims = new long[n];
		for (int d = 0; d < n; ++d) {
			gridDims[d] = (dims[d] - 1) / tileDims[d] + 1;
		}

		this.tilingAccess = tilingAccess;
		this.tileDims = tileDims;
	}

	@Override
	public RandomAccess<T> randomAccess(Interval arg0) {
		return randomAccess();
	}

	@Override
	public RandomAccess<T> randomAccess() {
		return new RequestibleTilingAccess();
	}

	private class RequestibleTilingAccess extends Point implements RandomAccess<T> {

		private long[] tempIndex;
		private long[] tempOffset;

		private RandomAccess<T> tempBlockAccess;

		private Map<Long, RandomAccess<T>> accesses;

		public RequestibleTilingAccess() {
			this.tempIndex = new long[gridDims.length];
			this.tempOffset = new long[gridDims.length];
			this.accesses = new HashMap<>();
		}

		@Override
		public T get() {
			long flatIndex = 0;
			for (int d = n - 1; d >= 0; --d) {
				tempIndex[d] = position[d] / tileDims[d];
				tempOffset[d] = position[d] % tileDims[d];
				flatIndex = flatIndex * gridDims[d] + tempIndex[d];
			}

			tempBlockAccess = accesses.get(flatIndex);

			if (tempBlockAccess == null) {
				tilingAccess.setPosition(flatIndex, 0);
				accesses.put(flatIndex, tempBlockAccess = tilingAccess.get().randomAccess());
			}

			// must be true as we bulk requested everything ...
			if (tempBlockAccess == null) {
				throw new IllegalStateException("Request first");
				// TODO later we can actually do the, potentially expensive,
				// request
				// here.
				// requestable.request(Arrays.asList(new IntervalRequest() {
				//
				// @Override
				// public Pair<Interval, AffineTransform> key() {
				// return null;
				// }
				// }));
			}

			tempBlockAccess.setPosition(tempOffset);
			return tempBlockAccess.get();
		}

		@Override
		public Sampler<T> copy() {
			return copyRandomAccess();
		}

		@Override
		public RandomAccess<T> copyRandomAccess() {
			// TODO
			return null;
		}
	}
}
