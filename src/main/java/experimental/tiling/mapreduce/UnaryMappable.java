
package experimental.tiling.mapreduce;

import experimental.compgraph.ComputationGraphNode;
import experimental.compgraph.Input;
import experimental.compgraph.UnaryStage;

public interface UnaryMappable<I, O> extends UnaryDistributable<I, O> {

	@Override
	default ComputationGraphNode<UnaryStage<Input<?>, I>, O> getDistributionPlan(
		final ComputationGraphNode<Input<?>, I> t)
	{
		return t.map(this);
	}
}
