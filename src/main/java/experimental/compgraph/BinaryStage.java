
package experimental.compgraph;

import net.imglib2.util.Pair;

public interface BinaryStage<II1 extends Input<?>, II2 extends Input<?>, I1, I2> extends
	Stage<BinaryInput<II1, II2>, Pair<I1, I2>>, BinaryInput<I1, I2>
{

	@Override
	UnaryStage<II1, I1> first();

	@Override
	UnaryStage<II2, I2> second();

	@Override
	default ComputationGraphNode<II1, I1> firstSource() {
		return first().source();
	}

	@Override
	default ComputationGraphNode<II2, I2> secondSource() {
		return second().source();
	}
}
