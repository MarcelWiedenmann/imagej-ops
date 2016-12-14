
package experimental.compgraph.request;

import net.imglib2.Interval;

import experimental.compgraph.tiling.LazyTile;

public interface TilingRequestable<T> extends Requestable<Interval, TileRequest, LazyTile<T>> {

	// NB: Marker interface for RAIs which can be requested by specifying an
	// Interval and an AffineTransform.
}
