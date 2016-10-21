
package experimental.compgraph.interfaces;

import java.util.List;

import net.imagej.ops.special.Output;

public interface ComputationGraphNode<O> extends Output<O> {

	public List<ComputationGraphNode<?>> getChildren();

	void addChild(ComputationGraphNode<?> child);

	boolean removeChild(ComputationGraphNode<?> child);

	ComputationGraphNode<O> copy();
}
