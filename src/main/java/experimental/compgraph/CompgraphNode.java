
package experimental.compgraph;

import org.scijava.Context;
import org.scijava.Contextual;

import experimental.compgraph.service.compgraph.CompgraphService;

public interface CompgraphNode extends Contextual {

	CompgraphService cgs();

	// -- Contextual --

	@Override
	default void setContext(final Context context) {
		context.inject(this);
	}
}
