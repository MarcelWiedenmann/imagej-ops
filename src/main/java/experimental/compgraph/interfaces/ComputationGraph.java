
package experimental.compgraph.interfaces;

import java.util.List;

public interface ComputationGraph<I, O> extends Iterable<ComputationGraphNode<?>> {

	List<ComputationGraphInputNode<?>> getStartNodes();

	List<ComputationGraphNode<?>> getEndNodes();

	ComputationGraph<I, O> copy();
}
