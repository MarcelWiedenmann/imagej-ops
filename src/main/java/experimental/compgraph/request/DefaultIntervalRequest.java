
package experimental.compgraph.request;

import net.imglib2.Interval;

public class DefaultIntervalRequest implements IntervalRequest {

	private final Interval key;

	public DefaultIntervalRequest(final Interval key) {
		this.key = key;
	}

	@Override
	public Interval key() {
		return key;
	}
}
