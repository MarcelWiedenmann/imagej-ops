package experimental.compgraph.request;

import net.imglib2.Interval;

public interface Tile extends Interval {

	long flatIndex();
}
