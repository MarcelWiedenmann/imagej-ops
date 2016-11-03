
package experimental.tiling.mapreduce;

import experimental.compgraph.interfaces.ComputationGraphNode;
import experimental.compgraph.interfaces.ComputationGraphNode.Input;
import experimental.compgraph.interfaces.ComputationGraphNode.UnaryStage;

public interface UnaryMappable<I, O> extends UnaryDistributable<I, O> {

	@Override
	default ComputationGraphNode<UnaryStage<Input<?>, I>, O> getDistributionPlan(
		final ComputationGraphNode<Input<?>, I> t)
	{
		return t.map(this);
	}
}
