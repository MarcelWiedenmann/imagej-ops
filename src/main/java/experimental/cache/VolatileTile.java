
package experimental.cache;

import net.imglib2.AbstractInterval;
import net.imglib2.Interval;
import net.imglib2.RandomAccess;

import experimental.tiling.Tile;

public class VolatileTile<T> extends AbstractInterval implements Tile<T> {

	public VolatileTile(final Interval interval) {
		super(interval);
		// TODO Auto-generated constructor stub
	}

	@Override
	public RandomAccess<T> randomAccess() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RandomAccess<T> randomAccess(final Interval interval) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getIndex() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long[] getOverlap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Interval getValidInterval() {
		// TODO Auto-generated method stub
		return null;
	}
}
