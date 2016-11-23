
package experimental.compgraph_old;

import experimental.algebra.compgraph.Input;
import experimental.algebra.compgraph.UnaryInput;

public interface UnaryStage<II extends Input<?>, I> extends Stage<II, I>, UnaryInput<I> {

	@Override
	ComputationGraphNode<II, I> source();
}
