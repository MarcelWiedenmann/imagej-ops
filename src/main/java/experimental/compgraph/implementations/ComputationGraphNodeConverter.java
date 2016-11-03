
package experimental.compgraph.implementations;

import experimental.compgraph.interfaces.ComputationGraphNode;
import experimental.compgraph.interfaces.UnaryComputationGraphInputNode;
import experimental.compgraph.interfaces.UnaryComputationGraphStageNode;

public class ComputationGraphNodeConverter {

	public static <I, O> UnaryComputationGraphStageNode<I, O> convertToStageNode(final ComputationGraphNode<I> parent,
		final UnaryComputationGraphInputNode<I, O> node)
	{
		return new DefaultUnaryComputationGraphStageNode<>(parent, node.getFunc().getIndependentInstance());
	}

	public static <I, O> UnaryComputationGraphInputNode<I, O> convertToInputNode(
		final UnaryComputationGraphInputNode<I, O> node)
	{
		return new DefaultUnaryComputationGraphInputNode<>(node.getFunc().getIndependentInstance());
	}
}
