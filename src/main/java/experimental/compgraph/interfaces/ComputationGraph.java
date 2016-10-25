
package experimental.compgraph.interfaces;

import java.util.List;

public interface ComputationGraph extends Iterable<ComputationGraphNode<?>> {

	List<ComputationGraphInputNode<?>> getStartNodes();

	List<ComputationGraphNode<?>> getEndNodes();

	ComputationGraph copy();
}
