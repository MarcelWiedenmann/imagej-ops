
package experimental.tiling;

import net.imagej.ops.special.function.UnaryFunctionOp;
import net.imglib2.util.Pair;

import experimental.compgraph.Fork;

// Collection that holds a ComputationGraph to store and lazily evaluate operations.
public interface OpsList<E> {

	<O> OpsList<O> append(UnaryFunctionOp<E, O> f);

	Fork<? extends OpsList<E>> fork();

	<E2> OpsList<Pair<E, E2>> join(OpsList<E2> c);
}
