
package experimental.compgraph.service.compgraph;

import org.scijava.service.AbstractService;

public class AbstractCompgraphService extends AbstractService implements CompgraphService {

	private final CompgraphNodeFactory factory;

	public AbstractCompgraphService(final CompgraphNodeFactory factory) {
		this.factory = factory;
	}

	@Override
	public CompgraphNodeFactory factory() {
		return factory;
	}
}
