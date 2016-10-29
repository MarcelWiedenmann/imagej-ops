
package experimental.compgraph.abstracts;

import java.util.ArrayList;
import java.util.Collection;

import experimental.compgraph.interfaces.ComputationGraphNode;
import experimental.compgraph.interfaces.UnaryComputationGraphCollectNode;

public abstract class AbstractUnaryComputationGraphCollectNode<I> implements UnaryComputationGraphCollectNode<I> {

	private final ArrayList<ComputationGraphNode<I>> parents;

	public AbstractUnaryComputationGraphCollectNode(final ComputationGraphNode<I> parent) {
		parents = new ArrayList<ComputationGraphNode<I>>();
		parents.add(parent);
	}

	public AbstractUnaryComputationGraphCollectNode(final ArrayList<ComputationGraphNode<I>> parents) {
		this.parents = parents;
	}

	@Override
	public Collection<ComputationGraphNode<I>> getParents() {
		// NB: Exposing the inner collection saves us from implementing all those collection methods.
		// Might be undesired/bad practice, though.
		// If this is changed, we should also copy the argument in ctor(ArrayList<ComputationGraphNode<I>>).
		return parents;
	}

	@Override
	public void addParent(final ComputationGraphNode<I> parent) {
		parents.add(parent);

	}

	@Override
	public boolean removeParent(final ComputationGraphNode<I> parent) {
		return parents.remove(parent);
	}
}
