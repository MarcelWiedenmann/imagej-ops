
package experimental.compgraph.node;

import java.util.Collection;
import java.util.stream.Collectors;

import experimental.compgraph.AbstractCompgraphSinkNode;
import experimental.compgraph.CompgraphSingleEdge;
import experimental.compgraph.LocalDataHandle;

public class LocalCollectionSink<O> extends AbstractCompgraphSinkNode<O, LocalDataHandle<O>, Collection<O>> {

	public LocalCollectionSink(final CompgraphSingleEdge<O> in) {
		super(in);
	}

	// -- AbstractCompgraphSinkNode --

	@Override
	protected Collection<O> getInternal(final LocalDataHandle<O> inData) {
		return inData.inner().collect(Collectors.toList());
	}
}
