
package experimental.tiling.mapreduce;

import experimental.compgraph.interfaces.ComputationGraphNode.ComputationGraphJoinNode;
import experimental.compgraph.interfaces.ComputationGraphNode.UnaryStage;

public interface BinaryMappable<I1, I2, O> extends BinaryDistributable<I1, I2, O> {

	@Override
	default ComputationGraphJoinNode<UnaryStage<?, I1>, UnaryStage<?, I2>, O> getDistributionPlan(
		final BinaryTilingNode<I1, I2> t)
	{
		// expected return signature: ComputationGraphNode<BinaryInput<UnaryStage<?, I1>, UnaryStage<?, I2>>, O>

		// real return signature: ComputationGraphNode<UnaryStage<BinaryInput<?, ?>, Pair<I1, I2>>, O> result =
		// t.map(wrapAsPair(this));
		throw new UnsupportedOperationException("not yet implemented");
	}
}
