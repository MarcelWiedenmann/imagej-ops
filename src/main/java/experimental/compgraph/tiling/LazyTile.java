
package experimental.compgraph.tiling;

import java.util.function.Supplier;

import net.imglib2.RandomAccessibleInterval;

public interface LazyTile<T> extends RandomAccessibleInterval<T>, Tile, Supplier<RandomAccessibleInterval<T>> {
	// NB: Marker Interface
}
