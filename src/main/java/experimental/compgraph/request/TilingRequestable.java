
package experimental.compgraph.request;

import net.imglib2.Interval;
import net.imglib2.RandomAccessibleInterval;

public interface TilingRequestable<T> extends Requestable<Interval, IntervalRequest, RandomAccessibleInterval<T>> {

	// NB: Marker interface for RAIs which can be requested by specifying an Interval and an AffineTransform.
}
