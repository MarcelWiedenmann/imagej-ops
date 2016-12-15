
package experimental.compgraph.request;

import java.util.List;

import experimental.compgraph.tiling.LazyTile;

public interface TilingRequestable<T> extends Requestable<List<Tile>, TilesRequest, List<LazyTile<T>>> {

	// NB: Marker interface for suppliers of lazy tiles given tile specifications.
}
