
package experimental.compgraph;

import net.imagej.ops.special.function.BinaryFunctionOp;
import net.imagej.ops.special.function.UnaryFunctionOp;

import experimental.compgraph.ComputationGraphNode.ComputationGraphForkNode;

public interface ComputationGraph<I extends Input<?>, O> extends UnaryInput<O>, Stage<I, O> {

	<OO> ComputationGraph<I, OO> append(UnaryFunctionOp<O, OO> f);

	// TODO: pair of graphs? join-graph?
	<I2 extends Input<?>, O2, OO> ComputationGraph<BinaryInput<I, I2>, OO> joinFirst(ComputationGraph<I2, O2> node,
		BinaryFunctionOp<O, O2, OO> f);

	<I2 extends Input<?>, O2, OO> ComputationGraph<BinaryInput<I2, I>, OO> joinSecond(ComputationGraph<I2, O2> node,
		BinaryFunctionOp<O2, O, OO> f);
	// --

	ComputationGraphForkNode<I, O> fork();

	<OO> ComputationGraph<I, OO> map(UnaryFunctionOp<O, OO> f);

	<OO> ComputationGraph<I, OO> flatAggregate(BinaryFunctionOp<O, O, OO> f);

	<OO> ComputationGraph<I, OO> treeAggregate(BinaryFunctionOp<O, O, OO> f);
}
