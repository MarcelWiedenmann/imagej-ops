package experimental.compgraph.img;

import java.util.HashMap;
import java.util.Map;

import net.imglib2.Cursor;
import net.imglib2.Point;
import net.imglib2.RandomAccess;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.Sampler;
import net.imglib2.img.array.ArrayImg;
import net.imglib2.img.basictypeaccess.array.FloatArray;
import net.imglib2.img.basictypeaccess.volatiles.array.VolatileFloatArray;
import net.imglib2.type.NativeType;
import net.imglib2.type.numeric.RealType;
import net.imglib2.type.numeric.real.FloatType;
import net.imglib2.util.IntervalIndexer;
import net.imglib2.util.Intervals;
import net.imglib2.view.Views;

import bdv.viewer.render.CompGraphFloatArrayLoader;
import experimental.compgraph.tiling.LazyTile;
import experimental.compgraph.tiling.request.TilingBulkRequestable;

public class DefaultCompGraphFloatArrayLoader<T extends NativeType<T> & RealType<T>>
		implements CompGraphFloatArrayLoader<T> {

	private VolatileFloatArray theEmptyArray;
	private TilingBulkRequestable<T, T> req;

	// we could also cache here.. but we don't.. ;-) Next node will cache, too.
	private final Map<Long, LazyTile<T>> res;

	private final int[] tileDims;
	private final long[] gridDims;

	private boolean staged = false;

	public DefaultCompGraphFloatArrayLoader(final TilingBulkRequestable<T, T> bulk) {
		this.req = bulk;
		this.res = new HashMap<>();
		this.gridDims = req.getGridDims();
		this.tileDims = new int[gridDims.length];

		for (int d = 0; d < gridDims.length; d++) {
			this.tileDims[d] = bulk.getTileDims()[d];
		}

		theEmptyArray = new VolatileFloatArray(32 * 32 * 32, false);
	}

	@Override
	public int getBytesPerElement() {
		return 4;
	}

	public void stage(long idx) {
		synchronized (res) {
			req.request(idx);
			staged = true;
			System.out.println("Staged");
		}
	}

	public void done() {
		synchronized (res) {
			if (staged != false) {
				res.putAll(req.flush());
				staged = false;
				System.out.println("Done");
			}
		}
	}

	public int[] getTileDims() {
		return tileDims;
	}

	public long[] getGridDims() {
		return gridDims;
	}

	@Override
	public VolatileFloatArray loadArray(int timepoint, int setup, int level, int[] dimensions, long[] min)
			throws InterruptedException {
		long[] tmp = new long[min.length];
		for (int d = 0; d < min.length; d++) {
			tmp[d] = min[d] / tileDims[d];
		}

		long idx = IntervalIndexer.positionToIndex(tmp, gridDims);

		// TODO
		// We have to do this if we request something which didnt get through
		// our staging process.
		LazyTile<T> lazyTile = res.get(idx);
		if (lazyTile == null) {
			// TODO activate pre-staging
			stage(idx);
			done();
			lazyTile = res.get(idx);
		}

		// TODO UGLY! has to change!!
		RandomAccessibleInterval<T> object = lazyTile.get();
		if (object instanceof ArrayImg && ((ArrayImg) object).update(null) instanceof FloatArray) {
			return new VolatileFloatArray(
					((ArrayImg<FloatType, FloatArray>) (object)).update(null).getCurrentStorageArray(), true);
		}
		final float[] data = new float[(int) Intervals.numElements(object)];
		Cursor<T> cursor = Views.iterable(object).cursor();
		for (int i = 0; i < data.length; i++) {
			data[i] = cursor.next().getRealFloat();
		}
		return new VolatileFloatArray(data, true);

	}

	@Override
	public VolatileFloatArray emptyArray(int[] dimensions) {
		int numEntities = 1;
		for (int i = 0; i < dimensions.length; ++i)
			numEntities *= dimensions[i];
		if (theEmptyArray.getCurrentStorageArray().length < numEntities)
			theEmptyArray = new VolatileFloatArray(numEntities, false);
		return theEmptyArray;
	}

	@Override
	public RandomAccess<?> getFakeCellRandomAccess(long[] globalDims) {
		return new FakeRandomAccess(globalDims);
	}

	private class FakeRandomAccess extends Point implements RandomAccess<T> {

		private final long[] globalDims;

		public FakeRandomAccess(final long[] globalDims) {
			super(globalDims.length);
			this.globalDims = globalDims;
		}

		private FakeRandomAccess(final long[] globalDims, final Point p) {
			super(p);
			this.globalDims = globalDims;
		}

		@Override
		public T get() {
			long[] tmp = new long[n];
			for (int d = 0; d < n; d++) {
				tmp[d] = position[d] / tileDims[d];
			}
			stage(IntervalIndexer.positionToIndex(tmp, globalDims));

			return null;
		}

		@Override
		public Sampler<T> copy() {
			return copyRandomAccess();
		}

		@Override
		public RandomAccess<T> copyRandomAccess() {
			return new FakeRandomAccess(globalDims, this);
		}

	}

}
