
package experimental.compgraph;

import org.scijava.AbstractContextual;
import org.scijava.plugin.Parameter;

import experimental.compgraph.service.CompgraphService;

public abstract class AbstractCompgraphNode extends AbstractContextual implements CompgraphNode {

	@Parameter
	private CompgraphService cgs;

	// -- CompgraphNode --

	@Override
	public CompgraphService cgs() {
		return cgs;
	}
}
