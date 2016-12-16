
package experimental.compgraph.tiling;

import net.imglib2.AbstractEuclideanSpace;
import net.imglib2.Positionable;
import net.imglib2.RealPositionable;

// TODO non abstract interval ...
public class DefaultTile extends AbstractEuclideanSpace implements Tile {

	private final long flatIndex;
	private long[] sourceMax;
	private long[] sourceMin;

	public DefaultTile(final long[] min, final long[] max, final long flatIndex) {
		super(min.length);
		this.flatIndex = flatIndex;
		this.sourceMin = min;
		this.sourceMax = max;
	}

	@Override
	public long flatIndex() {
		return flatIndex;
	}

	@Override
	public long min(int d) {
		return sourceMin[d];
	}

	@Override
	public void min(long[] min) {
		System.arraycopy(min, 0, min, 0, min.length);
	}

	@Override
	public void min(Positionable min) {
		min.setPosition(this.sourceMin);
	}

	@Override
	public long max(int d) {
		return sourceMax[d];
	}

	@Override
	public void max(long[] max) {
		System.arraycopy(max, 0, max, 0, max.length);
	}

	@Override
	public void max(Positionable max) {
		max.setPosition(this.sourceMax);
	}

	@Override
	public double realMin(int d) {
		return sourceMin[d];
	}

	@Override
	public void realMin(double[] min) {
		for (int d = 0; d < min.length; d++) {
			min[d] = this.sourceMin[d];
		}
	}

	@Override
	public void realMin(RealPositionable min) {
		min.setPosition(this.sourceMin);
	}

	@Override
	public double realMax(int d) {
		return sourceMax[d];
	}

	@Override
	public void realMax(double[] max) {
		for (int d = 0; d < max.length; d++) {
			max[d] = this.sourceMax[d];
		}
	}

	@Override
	public void realMax(RealPositionable max) {
		max.setPosition(this.sourceMax);
	}

	@Override
	public void dimensions(long[] dimensions) {
		for (int d = 0; d < dimensions.length; d++) {
			dimensions[d] = sourceMax[d] - sourceMin[d] + 1;
		}
	}

	@Override
	public long dimension(int d) {
		return sourceMax[d] - sourceMin[d] + 1;
	}
}
