
package experimental.compgraph.request;

import net.imglib2.Interval;

public interface UnaryInvertibleIntervalMapper {

	Interval invert(Interval i);
}
