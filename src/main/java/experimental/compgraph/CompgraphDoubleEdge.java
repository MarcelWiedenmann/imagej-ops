
package experimental.compgraph;

import java.util.HashSet;
import java.util.Set;

import net.imglib2.util.Pair;

public interface CompgraphDoubleEdge<IO1, IO2> extends CompgraphEdge<Pair<IO1, IO2>> {

	CompgraphEdge<IO1> first();

	CompgraphEdge<IO2> second();

	// -- CompgraphEdge --

	@Override
	default Set<CompgraphOutputNode<?, ?>> parents() {
		final Set<CompgraphOutputNode<?, ?>> firstParents = first().parents();
		final Set<CompgraphOutputNode<?, ?>> secondParents = second().parents();
		final HashSet<CompgraphOutputNode<?, ?>> parents = new HashSet<>(firstParents.size() + secondParents.size(), 1.0f);
		parents.addAll(firstParents);
		parents.addAll(secondParents);
		return parents;
	}
}
