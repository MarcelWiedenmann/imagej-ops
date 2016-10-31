
package experimental.compgraph.implementations;

import net.imagej.ops.special.function.BinaryFunctionOp;
import net.imagej.ops.special.function.UnaryFunctionOp;
import net.imglib2.util.Pair;

import experimental.compgraph.interfaces.ComputationGraph;

public class ComputationGraphFactory {

	public <I, O> ComputationGraph<I, O> create(final UnaryFunctionOp<I, O> op) {
		throw new UnsupportedOperationException("not yet implemented");
	}

	public <I1, I2, O> ComputationGraph<Pair<I1, I2>, O> create(final BinaryFunctionOp<I1, I2, O> op) {
		throw new UnsupportedOperationException("not yet implemented");
	}
}
