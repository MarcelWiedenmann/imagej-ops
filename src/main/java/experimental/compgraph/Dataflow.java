
package experimental.compgraph;

public interface Dataflow<IO, INNER> extends CompgraphNodeBodyOperand<IO> {
	// NB: Marker interface for entities carrying data through compgraph edges/"channels".
	// NB: INNER is informally restricted to be some kind of container or supplier of IO.

	INNER inner();
}
