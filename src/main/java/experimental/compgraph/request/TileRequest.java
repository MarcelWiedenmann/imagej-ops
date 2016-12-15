
package experimental.compgraph.request;

public interface TileRequest extends Request<Tile> {

	// NB: This is the interval in global coordinates, specifying the request.
	@Override
	public Tile key();
	
}
