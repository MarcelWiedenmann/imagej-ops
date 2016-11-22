
package experimental.algebra.compgraph;

import net.imglib2.util.Pair;

import experimental.compgraph.ComputationGraphNode;

public interface BinaryInput<I1, I2> extends Input<Pair<I1, I2>> {

	UnaryInput<I1> first();

	UnaryInput<I2> second();

	default ComputationGraphNode<?, I1> firstSource() {
		return first().source();
	}

	default ComputationGraphNode<?, I2> secondSource() {
		return second().source();
	}
}
