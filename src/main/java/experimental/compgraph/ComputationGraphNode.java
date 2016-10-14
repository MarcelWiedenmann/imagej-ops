
package experimental.compgraph;

import net.imagej.ops.special.Output;

public interface ComputationGraphNode<O> extends Output<O> {

	// TODO: We will probably also have to maintain references in leaf direction ("children"). #children per node is not
	// limited (in contrast to #parents): list or similar & (un-)registering routine

	ComputationGraphNode<O> copy();
}
