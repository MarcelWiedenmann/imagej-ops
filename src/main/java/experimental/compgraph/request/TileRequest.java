
package experimental.compgraph.request;

import java.util.List;

public interface TileRequest extends Request<List<Tile>> {

	// NB: This is the interval in global coordinates, specifying the request.
	@Override
	public Tile key();
	
}
