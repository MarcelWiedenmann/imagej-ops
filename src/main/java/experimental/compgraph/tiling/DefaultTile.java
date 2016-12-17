
package experimental.compgraph.tiling;

import net.imglib2.AbstractEuclideanSpace;
import net.imglib2.Positionable;
import net.imglib2.RealPositionable;

public class DefaultTile extends AbstractEuclideanSpace implements Tile {

	private final long flatIndex;
	private final long[] maxAsLong;
	private final long[] minAsLong;

	public DefaultTile(final long flatIndex, final long[] min, final long[] max) {
		super(min.length);
		this.flatIndex = flatIndex;
		this.minAsLong = min;
		this.maxAsLong = max;
	}

	// -- Tile --

	@Override
	public long flatIndex() {
		return flatIndex;
	}

	@Override
	public long[] min() {
		return minAsLong;
	}

	@Override
	public long[] max() {
		return maxAsLong;
	}

	// -- Interval --

	@Override
	public long min(final int d) {
		return minAsLong[d];
	}

	@Override
	public void min(final long[] min) {
		System.arraycopy(minAsLong, 0, min, 0, min.length);
	}

	@Override
	public void min(final Positionable min) {
		min.setPosition(this.minAsLong);
	}

	@Override
	public long max(final int d) {
		return maxAsLong[d];
	}

	@Override
	public void max(final long[] max) {
		System.arraycopy(maxAsLong, 0, max, 0, max.length);
	}

	@Override
	public void max(final Positionable max) {
		max.setPosition(this.maxAsLong);
	}

	@Override
	public double realMin(final int d) {
		return minAsLong[d];
	}

	@Override
	public void realMin(final double[] min) {
		for (int d = 0; d < min.length; d++) {
			min[d] = this.minAsLong[d];
		}
	}

	@Override
	public void realMin(final RealPositionable min) {
		min.setPosition(this.minAsLong);
	}

	@Override
	public double realMax(final int d) {
		return maxAsLong[d];
	}

	@Override
	public void realMax(final double[] max) {
		for (int d = 0; d < max.length; d++) {
			max[d] = this.maxAsLong[d];
		}
	}

	@Override
	public void realMax(final RealPositionable max) {
		max.setPosition(this.maxAsLong);
	}

	@Override
	public void dimensions(final long[] dimensions) {
		for (int d = 0; d < dimensions.length; d++) {
			dimensions[d] = maxAsLong[d] - minAsLong[d] + 1;
		}
	}

	@Override
	public long dimension(final int d) {
		return maxAsLong[d] - minAsLong[d] + 1;
	}
}
