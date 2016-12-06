
package experimental.compgraph;

import net.imglib2.util.Pair;

public interface CompgraphBinaryNode<IN extends DataHandle<Pair<I1, I2>, ?>, I1, I2, O, OUT extends DataHandle<O, ?>>
	extends CompgraphInnerNode<IN, Pair<I1, I2>, O, OUT>
{

	// -- CompgraphInnerNode --

	@Override
	CompgraphDoubleEdge<I1, I2> in();
}
