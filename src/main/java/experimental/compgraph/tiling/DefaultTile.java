
package experimental.compgraph.tiling;

import net.imglib2.AbstractEuclideanSpace;
import net.imglib2.AbstractInterval;

// TODO non abstract interval ...
public class DefaultTile extends AbstractEuclideanSpace implements Tile {

	private final long flatIndex;

	public DefaultTile(final long[] min, final long[] max, final long flatIndex) {
		super(min.length);
		this.flatIndex = flatIndex;
	}

	@Override
	public long flatIndex() {
		return flatIndex;
	}

	@Override
	public boolean isComplete() {
		return false;
	}
}
