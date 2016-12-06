
package experimental.compgraph;

import java.util.stream.Stream;

public class LocalDataHandle<IO> implements DataHandle<IO, Stream<IO>> {

	Stream<IO> inner;

	public LocalDataHandle(final Stream<IO> inner) {
		this.inner = inner;
	}

	@Override
	public Stream<IO> inner() {
		return inner;
	}
}
