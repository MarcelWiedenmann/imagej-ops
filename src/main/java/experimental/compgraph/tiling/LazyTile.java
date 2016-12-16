
package experimental.compgraph.tiling;

import net.imglib2.RandomAccess;
import net.imglib2.RandomAccessibleInterval;

import experimental.compgraph.request.Tile;

// NB: Sub-tile region in a tiling that is accessible, i.e. contains data.
public interface LazyTile<T> extends RandomAccessibleInterval<T>, Tile {

	public static interface LazyTileRandomAccess<T> extends RandomAccess<T> {}

	boolean isComplete();;
}
