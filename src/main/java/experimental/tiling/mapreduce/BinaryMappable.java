
package experimental.tiling.mapreduce;

import net.imglib2.util.Pair;

import experimental.compgraph.BinaryAsUnaryFunctionOp;
import experimental.compgraph.BinaryInput;
import experimental.compgraph.BinaryStage;
import experimental.compgraph.ComputationGraphNode;
import experimental.compgraph.ComputationGraphNode.ComputationGraphJoinNode;
import experimental.compgraph.UnaryStage;

public interface BinaryMappable<I1, I2, O> extends BinaryDistributable<I1, I2, O> {

	@Override
	default ComputationGraphJoinNode<I1, I2, BinaryStage<?, ?, I1, I2>, O> getDistributionPlan(
		final BinaryTilingNode<I1, I2> t)
	{
		// expected return signature: ComputationGraphNode<BinaryInput<UnaryStage<?, I1>, UnaryStage<?, I2>>, O>

		final ComputationGraphNode<UnaryStage<BinaryInput<?, ?>, Pair<I1, I2>>, O> map = t.map(
			new BinaryAsUnaryFunctionOp<>(this));

		t.map

		// real return signature: ComputationGraphNode<UnaryStage<BinaryInput<?, ?>, Pair<I1, I2>>, O> result =
		// t.map(wrapAsPair(this));
		throw new UnsupportedOperationException("not yet implemented");
	}
}
