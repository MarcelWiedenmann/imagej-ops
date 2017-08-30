
package experimental.compgraph;

import net.imglib2.util.Pair;

public interface CompgraphBinaryNode<I1, I2, IN extends DataHandle<Pair<I1, I2>, ?>, O, OUT extends DataHandle<O, ?>>
	extends CompgraphInnerNode<Pair<I1, I2>, IN, O, OUT>
{

	// -- CompgraphInnerNode --

	@Override
	CompgraphDoubleEdge<I1, I2> in();
}
