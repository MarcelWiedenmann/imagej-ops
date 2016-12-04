
package experimental.compgraph;

public interface CompgraphTransformNodeBody<IN extends CompgraphEdge<I>, I, O, OUT extends CompgraphEdge<O>> extends
	CompgraphNodeBody<IN, I, O, OUT>
{

	// NB: Marker interface for the processing component of a compgraph node which operates on the incoming edge's format.
}
