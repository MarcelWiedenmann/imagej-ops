
package experimental.compgraph.request;

import net.imglib2.Interval;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.realtransform.AffineTransform;
import net.imglib2.util.Pair;

public interface RequestableRai<T> extends
	Requestable<Pair<Interval, AffineTransform>, IntervalTransformRequest, RandomAccessibleInterval<T>>
{

	// NB: Marker interface for RAIs which can be requested by specifying an Interval and an AffineTransform.
}
