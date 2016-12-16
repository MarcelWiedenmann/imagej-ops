
package experimental.compgraph.request;

import java.util.Iterator;

import experimental.compgraph.tiling.LazyTile;

public interface TilingRequestable<T> extends Requestable<Iterator<Tile>, TilesRequest, Iterator<LazyTile<T>>> {

	// NB: Marker interface for suppliers of lazy tiles given tile
	// specifications.
}
