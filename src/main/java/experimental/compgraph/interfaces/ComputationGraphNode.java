
package experimental.compgraph.interfaces;

import java.util.List;

import net.imagej.ops.special.Output;

public interface ComputationGraphNode<O> extends Output<O> {

	public List<ComputationGraphStageNode<?>> getChildren();

	public boolean hasChild(ComputationGraphStageNode<?> child);

	public ComputationGraphStageNode<?> getChild(int index);

	// NB: Parent relationships of stage nodes are NOT intended to be handled by addChild/removeChild.
	// Parent relationships must be established by the parent setters of the stage nodes.

	boolean addChild(final ComputationGraphStageNode<?> child);

	boolean removeChild(final ComputationGraphStageNode<?> child);

	ComputationGraphNode<O> copyUpstream();
}
