
package experimental.compgraph;

import java.util.stream.Stream;

public class DefaultDataflow<IO> implements Dataflow<IO, Stream<IO>> {

	Stream<IO> inner;

	public DefaultDataflow(final Stream<IO> inner) {
		this.inner = inner;
	}

	@Override
	public Stream<IO> inner() {
		return inner;
	}
}
