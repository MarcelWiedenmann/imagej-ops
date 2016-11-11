
package experimental.tiling;

import net.imagej.ops.special.function.UnaryFunctionOp;

import experimental.compgraph.ComputationGraph;
import experimental.compgraph.ComputationGraphNode;
import experimental.compgraph.UnaryInput;

public interface DistributedCollection<I, O> extends ComputationGraph<UnaryInput<I>, O> {

	// -- TEST - separate collection from graph?
	void set(ComputationGraphNode<? extends UnaryInput<I>, O> node);

	default <OO> DistributedCollection<O, OO> mapTest(final UnaryFunctionOp<O, OO> f) {

		final ComputationGraphNode<UnaryInput<I>, O> test = null;

		set(test);

		final ComputationGraphNode<? extends UnaryInput<O>, OO> map = test.map(f);

		final DistributedCollection<O, OO> test2 = null;

		test2.set(map);

	}
	// --
}
