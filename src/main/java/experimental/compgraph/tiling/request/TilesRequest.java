
package experimental.compgraph.tiling.request;

import java.util.Iterator;

import experimental.compgraph.request.Request;
import experimental.compgraph.tiling.Tile;

// NB: Contains a list of sub-tile regions that can be requested during a 'configure' phase of a tile-processing
// compgraph. This request is ought to be answered as a whole.
public interface TilesRequest extends Request<Iterator<Tile>> {

	@Override
	public Iterator<Tile> key();
}
