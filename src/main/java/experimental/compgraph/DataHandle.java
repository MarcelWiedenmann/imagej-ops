
package experimental.compgraph;

public interface DataHandle<IO, INNER> {
	// Interface for entities carrying data through compgraph edges/"channels".
	// NB: INNER is informally restricted to be some kind of container or supplier of IO. We do not ensure this in a
	// formal way because we want to enable easy plugging in of every kind of data container (such as simple Java Arrays
	// etc.) without further wrapping.

	INNER inner();
}
