
package experimental.compgraph;

import net.imagej.ops.special.Output;
import net.imagej.ops.special.function.UnaryFunctionOp;

public interface UnaryComputationGraphNode<I, O> extends Output<O> {

	public UnaryComputationGraphNode<?, I> getParent();

	public UnaryFunctionOp<I, O> getOp();

	public UnaryComputationGraphNode<I, O> copy();
}
