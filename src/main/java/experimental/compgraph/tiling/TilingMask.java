package experimental.compgraph.tiling;

import net.imglib2.Interval;
import net.imglib2.util.Pair;

public interface TilingMask extends Iterable<Pair<Long, Interval>>, TilingActivator {

}
