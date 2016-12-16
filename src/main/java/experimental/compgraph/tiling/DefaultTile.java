
package experimental.compgraph.tiling;

import net.imglib2.AbstractEuclideanSpace;
import net.imglib2.Positionable;
import net.imglib2.RealPositionable;

// TODO non abstract interval ...
public class DefaultTile extends AbstractEuclideanSpace implements Tile {

	private final long flatIndex;
	private final long[] max;
	private final long[] min;

	public DefaultTile(final long[] min, final long[] max, final long flatIndex) {
		super(min.length);
		this.flatIndex = flatIndex;
		this.min = min;
		this.max = max;
	}

	// -- Tile --

	@Override
	public long flatIndex() {
		return flatIndex;
	}

	@Override
	public long[] min() {
		return min;
	}

	@Override
	public long[] max() {
		return max;
	}

	// -- Interval --

	@Override
	public long min(final int d) {
		return min[d];
	}

	@Override
	public void min(final long[] min) {
		System.arraycopy(min, 0, min, 0, min.length);
	}

	@Override
	public void min(final Positionable min) {
		min.setPosition(this.min);
	}

	@Override
	public long max(final int d) {
		return max[d];
	}

	@Override
	public void max(final long[] max) {
		System.arraycopy(max, 0, max, 0, max.length);
	}

	@Override
	public void max(final Positionable max) {
		max.setPosition(this.max);
	}

	@Override
	public double realMin(final int d) {
		return min[d];
	}

	@Override
	public void realMin(final double[] min) {
		for (int d = 0; d < min.length; d++) {
			min[d] = this.min[d];
		}
	}

	@Override
	public void realMin(final RealPositionable min) {
		min.setPosition(this.min);
	}

	@Override
	public double realMax(final int d) {
		return max[d];
	}

	@Override
	public void realMax(final double[] max) {
		for (int d = 0; d < max.length; d++) {
			max[d] = this.max[d];
		}
	}

	@Override
	public void realMax(final RealPositionable max) {
		max.setPosition(this.max);
	}

	@Override
	public void dimensions(final long[] dimensions) {
		for (int d = 0; d < dimensions.length; d++) {
			dimensions[d] = max[d] - min[d] + 1;
		}
	}

	@Override
	public long dimension(final int d) {
		return max[d] - min[d] + 1;
	}
}
