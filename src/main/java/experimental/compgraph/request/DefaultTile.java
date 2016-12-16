
package experimental.compgraph.request;

import net.imglib2.AbstractInterval;

public class DefaultTile extends AbstractInterval implements Tile {

	private final long flatIndex;

	public DefaultTile(final long[] min, final long[] max, final long flatIndex) {
		super(min, max);
		this.flatIndex = flatIndex;
	}

	@Override
	public long flatIndex() {
		return flatIndex;
	}
}
