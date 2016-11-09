
package experimental.compgraph;

public interface UnaryStage<II extends Input<?>, I> extends Stage<II, I>, UnaryInput<I> {

	@Override
	ComputationGraphNode<II, I> source();
}
