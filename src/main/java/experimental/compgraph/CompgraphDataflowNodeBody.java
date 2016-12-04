
package experimental.compgraph;

public interface CompgraphDataflowNodeBody<IN extends Dataflow<I, ?>, I, O, OUT extends Dataflow<O, ?>> extends
	CompgraphNodeBody<IN, I, O, OUT>
{

	// NB: Marker interface for the processing component of a compgraph node which operates on the incoming edge's data.
}
