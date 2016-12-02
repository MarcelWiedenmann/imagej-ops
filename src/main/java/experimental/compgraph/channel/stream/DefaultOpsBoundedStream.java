
package experimental.compgraph.channel.stream;

import java.util.Collection;
import java.util.stream.Stream;

public class DefaultOpsBoundedStream<I> implements OpsBoundedStream<I> {

	private final Stream<I> source;

	public DefaultOpsBoundedStream(final Collection<I> source) {
		this(source.parallelStream());
	}

	public DefaultOpsBoundedStream(Stream<I> source) {
		if (!source.isParallel()) {
			source = source.parallel();
		}
		this.source = source;
	}
}
