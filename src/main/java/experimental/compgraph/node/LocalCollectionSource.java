
package experimental.compgraph.node;

import java.util.Collection;

import experimental.compgraph.AbstractCompgraphSourceNode;
import experimental.compgraph.LocalDataHandle;

public class LocalCollectionSource<IO> extends AbstractCompgraphSourceNode<IO, LocalDataHandle<IO>> {

	public LocalCollectionSource(final Collection<IO> inData) {
		super(new LocalDataHandle<>(inData.parallelStream()));
	}
}
