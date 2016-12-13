
package experimental.compgraph;

import java.util.stream.Stream;

public class LocalDataHandle<IO> implements DataHandle<IO, Stream<IO>> {

	private final Stream<IO> inner;

	public LocalDataHandle(final Stream<IO> inner) {
		this.inner = inner;
	}

	// -- DataHandle --

	@Override
	public Stream<IO> inner() {
		return inner;
	}
}
