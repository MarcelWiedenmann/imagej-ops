
package experimental.compgraph.request;

import net.imglib2.Interval;

public interface TileRequest extends Request<Interval> {

	// NB: This is the interval in global coordinates, specifying the request.
	@Override
	public Interval key();
	
}
