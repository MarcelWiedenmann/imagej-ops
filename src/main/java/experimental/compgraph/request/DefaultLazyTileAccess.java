package experimental.compgraph.request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.imglib2.Point;
import net.imglib2.RandomAccess;
import net.imglib2.RandomAccessible;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.Sampler;

public class DefaultLazyTileAccess<T> extends Point implements LazyTileAccess<T> {

	private final RequestableRai<T> req;
	private final List<IntervalRequest> intervals;

	private boolean requested = false;

	private final long[] gridDims;
	private final long[] dims;
	private final long[] tileDims;

	// TODO use LONG list here!!!
	private final List<RandomAccessible<T>> tiles;

	public DefaultLazyTileAccess(final List<IntervalRequest> intervals, final RequestableRai<T> req, final long[] dims,
			final long[] tileDims) {
		super(1);

		this.req = req;
		this.intervals = intervals;
		this.dims = dims;

		//
		this.tileDims = new long[dims.length];
		this.gridDims = new long[n];
		this.tiles = new ArrayList<>();

		//
		for (int d = 0; d < n; ++d) {
			gridDims[d] = (dims[d] - 1) / tileDims[d] + 1;
		}
	}

	@Override
	public RandomAccessible<T> get() {
		synchronized (this) {
			if (!requested) {
				request();
				requested = true;
			}
		}

		return tiles.get((int) position[0]);
	}

	private void request() {
		final Map<Long, RandomAccessibleInterval<T>> requests = new HashMap<>();
		
		for (final IntervalRequest req : intervals) {
		}

		// here I have to fill the tiles...
	}

	@Override
	public RandomAccess<RandomAccessible<T>> copyRandomAccess() {
		return null;
	}

	@Override
	public Sampler<RandomAccessible<T>> copy() {
		return null;
	}

}
