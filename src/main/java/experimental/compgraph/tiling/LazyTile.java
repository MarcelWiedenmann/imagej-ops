
package experimental.compgraph.tiling;

import net.imglib2.RandomAccessibleInterval;

import experimental.compgraph.request.Tile;

public interface LazyTile<T> extends RandomAccessibleInterval<T>, Tile {
	// NB: Sub-tile region in a tiling that is accessible, i.e. contains data.
}
