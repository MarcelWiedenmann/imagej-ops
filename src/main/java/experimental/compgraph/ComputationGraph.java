
package experimental.compgraph;

import net.imagej.ops.special.function.BinaryFunctionOp;
import net.imagej.ops.special.function.UnaryFunctionOp;

import experimental.algebra.compgraph.BinaryInput;
import experimental.algebra.compgraph.Input;
import experimental.algebra.compgraph.UnaryInput;

public interface ComputationGraph<I extends Input<?>, O> extends UnaryInput<O>, Stage<I, O> {

	ComputationGraphNode<? extends Input<?>, O> last();

	<OO> ComputationGraph<I, OO> append(UnaryFunctionOp<O, OO> f);

	Fork<ComputationGraphNode<I, O>> fork();

	<I2 extends Input<?>, O2, OO> ComputationGraph<BinaryInput<I, I2>, OO> joinFirst(ComputationGraph<I2, O2> node,
		BinaryFunctionOp<O, O2, OO> f);

	<I2 extends Input<?>, O2, OO> ComputationGraph<BinaryInput<I2, I>, OO> joinSecond(ComputationGraph<I2, O2> node,
		BinaryFunctionOp<O2, O, OO> f);
}
