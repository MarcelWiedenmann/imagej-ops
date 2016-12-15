
package experimental.compgraph.request;

import java.util.List;

// NB: Contains a list of sub-tile regions that can be requested during a 'configure' phase of a tile-processing
// compgraph. This request is ought to be answered as a whole.
public interface TilesRequest extends Request<List<Tile>> {

	@Override
	public List<Tile> key();
}
