
package experimental.compgraph.body;

import experimental.compgraph.CompgraphDataflowNodeBody;
import experimental.compgraph.Dataflow;

public interface Source<IO, INOUT extends Dataflow<IO, ?>> extends CompgraphDataflowNodeBody<INOUT, IO, IO, INOUT> {

	// TODO: derived classes will supply dataflows from general input types such as Collection<I> etc.
}
