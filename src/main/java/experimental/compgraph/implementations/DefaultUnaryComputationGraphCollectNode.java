
package experimental.compgraph.implementations;

import java.util.ArrayList;
import java.util.Iterator;

import experimental.compgraph.abstracts.AbstractUnaryComputationGraphCollectNode;
import experimental.compgraph.interfaces.ComputationGraphNode;

public class DefaultUnaryComputationGraphCollectNode<I> extends AbstractUnaryComputationGraphCollectNode<I> {

	public DefaultUnaryComputationGraphCollectNode(final ComputationGraphNode<I> parent) {
		super(parent);
	}

	private DefaultUnaryComputationGraphCollectNode(final DefaultUnaryComputationGraphCollectNode<I> node) {
		super(new ArrayList<>(node.getParents().size()));
		// FIXME: Fork(-join) graphs and copying: Forked branches have to reference the same fork again.
		for (final ComputationGraphNode<I> parent : node.getParents()) {
			addParent(parent.copy());
		}
	}

	@Override
	public Iterator<I> out() {
		return getParents().parallelStream().map(parent -> parent.out()).iterator();
	}

	@Override
	public DefaultUnaryComputationGraphCollectNode<I> copy() {
		return new DefaultUnaryComputationGraphCollectNode<>(this);
	}
}
