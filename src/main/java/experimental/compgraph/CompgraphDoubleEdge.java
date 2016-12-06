
package experimental.compgraph;

import net.imglib2.util.Pair;

public interface CompgraphDoubleEdge<I1, I2> extends CompgraphEdge<Pair<I1, I2>> {

	CompgraphEdge<I1> first();

	CompgraphEdge<I2> second();
}
