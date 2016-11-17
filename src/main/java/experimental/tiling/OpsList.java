
package experimental.tiling;

import net.imagej.ops.special.function.UnaryFunctionOp;

import experimental.compgraph.Fork;

// Collection that holds a ComputationGraph to store and lazily evaluate operations.
public interface OpsList<I, O> {

	<OO> OpsList<I, OO> append(UnaryFunctionOp<O, OO> f);

	Fork<? extends OpsList<I, O>> fork();

	<I2, O2, K> JoinedLazyCollection<I, I2, O, O2> join(OpsList<I2, O2> c);
}
