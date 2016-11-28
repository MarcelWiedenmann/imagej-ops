
package experimental.compgraph.algebra;

import java.util.function.BiPredicate;

import net.imglib2.util.Pair;

import experimental.compgraph.CompgraphEdge;
import experimental.compgraph.CompgraphNodeBody;
import experimental.compgraph.CompgraphSingleEdge;

public interface Join<IN1 extends CompgraphEdge<I1>, IN2 extends CompgraphEdge<I2>, I1, I2, OUT extends CompgraphSingleEdge<Pair<I1, I2>>>
	extends CompgraphNodeBody<Pair<IN1, IN2>, OUT>
{

	BiPredicate<I1, I2> func();
}
