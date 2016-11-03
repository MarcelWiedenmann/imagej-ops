
package experimental.tiling.mapreduce;

import net.imagej.ops.special.function.BinaryFunctionOp;
import net.imagej.ops.special.function.UnaryFunctionOp;
import net.imglib2.util.Pair;

import experimental.compgraph.interfaces.ComputationGraphNode.ComputationGraphJoinNode;
import experimental.compgraph.interfaces.ComputationGraphNode.UnaryStage;

public interface BinaryDistributable<I1, I2, O> extends BinaryFunctionOp<I1, I2, O> {

	ComputationGraphJoinNode<UnaryStage<?, I1>, UnaryStage<?, I2>, O> getDistributionPlan(BinaryTilingNode<I1, I2> t);

	default UnaryFunctionOp<Pair<I1, I2>, O> wrapAsPair(final BinaryFunctionOp<I1, I2, O> f) {
		throw new UnsupportedOperationException("not yet implemented");
	};
}
