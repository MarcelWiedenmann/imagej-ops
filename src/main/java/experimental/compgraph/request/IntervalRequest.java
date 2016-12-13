
package experimental.compgraph.request;

import net.imglib2.Interval;

public interface IntervalRequest extends Request<Interval> {

	// NB: Marker interface for requests which specify their target by an Interval.
}
