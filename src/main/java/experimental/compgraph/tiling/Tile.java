
package experimental.compgraph.tiling;

import net.imglib2.Interval;

// NB: Identifies a sub-tile region in a tiling by specifying the flat index of its containing tile and an interval on
// this tile (possibly the entire tile).
public interface Tile extends Interval {

	long flatIndex();

	long[] min();

	long[] max();
}
