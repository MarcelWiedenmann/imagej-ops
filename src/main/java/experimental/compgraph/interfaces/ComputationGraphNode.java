
package experimental.compgraph.interfaces;

import net.imagej.ops.special.Output;

public interface ComputationGraphNode<O> extends Output<O> {

	ComputationGraphNode<O> copyUpstream();
}
