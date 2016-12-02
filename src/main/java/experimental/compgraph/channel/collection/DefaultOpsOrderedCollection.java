
package experimental.compgraph.channel.collection;

import java.util.Collection;
import java.util.stream.Stream;

public class DefaultOpsOrderedCollection<I> implements OpsOrderedCollection<I> {

	private final Stream<I> source;

	public DefaultOpsOrderedCollection(final Collection<I> source) {
		this(source.parallelStream());
	}

	public DefaultOpsOrderedCollection(Stream<I> source) {
		if (!source.isParallel()) {
			source = source.parallel();
		}
		this.source = source;
	}
}
