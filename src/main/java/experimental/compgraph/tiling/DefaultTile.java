
package experimental.compgraph.tiling;

import net.imglib2.AbstractEuclideanSpace;
import net.imglib2.Positionable;
import net.imglib2.RealPositionable;

public class DefaultTile extends AbstractEuclideanSpace implements Tile {

	private final long flatIndex;
	private final long[] globalMax;
	private final long[] globalMin;

	public DefaultTile(final long[] min, final long[] max, final long flatIndex) {
		super(min.length);
		this.flatIndex = flatIndex;
		this.globalMin = min;
		this.globalMax = max;
	}

	// -- Tile --

	@Override
	public long flatIndex() {
		return flatIndex;
	}

	@Override
	public long[] min() {
		return globalMin;
	}

	@Override
	public long[] max() {
		return globalMax;
	}

	// -- Interval --

	@Override
	public long min(final int d) {
		return globalMin[d];
	}

	@Override
	public void min(final long[] min) {
		System.arraycopy(min, 0, min, 0, min.length);
	}

	@Override
	public void min(final Positionable min) {
		min.setPosition(this.globalMin);
	}

	@Override
	public long max(final int d) {
		return globalMax[d];
	}

	@Override
	public void max(final long[] max) {
		System.arraycopy(max, 0, max, 0, max.length);
	}

	@Override
	public void max(final Positionable max) {
		max.setPosition(this.globalMax);
	}

	@Override
	public double realMin(final int d) {
		return globalMin[d];
	}

	@Override
	public void realMin(final double[] min) {
		for (int d = 0; d < min.length; d++) {
			min[d] = this.globalMin[d];
		}
	}

	@Override
	public void realMin(final RealPositionable min) {
		min.setPosition(this.globalMin);
	}

	@Override
	public double realMax(final int d) {
		return globalMax[d];
	}

	@Override
	public void realMax(final double[] max) {
		for (int d = 0; d < max.length; d++) {
			max[d] = this.globalMax[d];
		}
	}

	@Override
	public void realMax(final RealPositionable max) {
		max.setPosition(this.globalMax);
	}

	@Override
	public void dimensions(final long[] dimensions) {
		for (int d = 0; d < dimensions.length; d++) {
			dimensions[d] = globalMax[d] - globalMin[d] + 1;
		}
	}

	@Override
	public long dimension(final int d) {
		return globalMax[d] - globalMin[d] + 1;
	}
}
