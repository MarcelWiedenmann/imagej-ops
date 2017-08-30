
package experimental.compgraph.node;

import java.util.Collection;
import java.util.stream.Collectors;

import experimental.compgraph.AbstractCompgraphSinkNode;
import experimental.compgraph.CompgraphSingleEdge;
import experimental.compgraph.LocalDataHandle;

public class LocalCollectionSink<IO> extends AbstractCompgraphSinkNode<IO, LocalDataHandle<IO>, Collection<IO>> {

	public LocalCollectionSink(final CompgraphSingleEdge<IO> in) {
		super(in);
	}

	// -- AbstractCompgraphSinkNode --

	@Override
	protected Collection<IO> getInternal(final LocalDataHandle<IO> inData) {
		return inData.inner().collect(Collectors.toList());
	}
}
