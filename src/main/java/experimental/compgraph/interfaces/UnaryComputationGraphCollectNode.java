
package experimental.compgraph.interfaces;

import java.util.Collection;
import java.util.Iterator;

// TODO: How do split & collect nodes & computation branches relate?

public interface UnaryComputationGraphCollectNode<I> extends ComputationGraphStageNode<Iterator<I>> {

	Collection<ComputationGraphNode<I>> getParents();

	void addParent(final ComputationGraphNode<I> parent);

	boolean removeParent(final ComputationGraphNode<I> parent);
}
