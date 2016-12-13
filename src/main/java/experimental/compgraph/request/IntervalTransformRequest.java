
package experimental.compgraph.request;

import net.imglib2.Interval;
import net.imglib2.realtransform.AffineTransform;
import net.imglib2.util.Pair;

public interface IntervalTransformRequest extends Request<Pair<Interval, AffineTransform>> {

	// NB: Marker interface for requests which specify their target by an AffineTransform which will be applied on a
	// source Interval.
}
