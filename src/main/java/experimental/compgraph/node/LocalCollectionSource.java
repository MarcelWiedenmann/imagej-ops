
package experimental.compgraph.node;

import java.util.Collection;

import experimental.compgraph.AbstractCompgraphSourceNode;
import experimental.compgraph.LocalDataHandle;

public class LocalCollectionSource<O> extends AbstractCompgraphSourceNode<O, LocalDataHandle<O>> {

	public LocalCollectionSource(final Collection<O> inData) {
		super(new LocalDataHandle<>(inData.parallelStream()));
	}
}
