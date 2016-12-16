
package experimental.compgraph.tiling.request;

import java.util.Iterator;

import experimental.compgraph.request.Requestable;
import experimental.compgraph.tiling.LazyTile;
import experimental.compgraph.tiling.Tile;

public interface TilingRequestable<T> extends Requestable<Iterator<Tile>, TilesRequest, Iterator<LazyTile<T>>> {

	// NB: Marker interface for suppliers of lazy tiles given tile
	// specifications.
}
