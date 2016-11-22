
package experimental.compgraph;

import experimental.algebra.compgraph.Input;

public interface Stage<II extends Input<?>, I> extends Input<I> {
	// NB: Marker interface.
}
