
package experimental.compgraph.body;

import java.util.function.Function;

import experimental.compgraph.CompgraphEdge;
import experimental.compgraph.CompgraphSingleEdge;

public class DefaultMap<IN extends CompgraphEdge<I>, I, O, OUT extends CompgraphSingleEdge<O>> implements
	Map<IN, I, O, OUT>
{

	private final Function<? super I, O> f;

	public DefaultMap(final Function<? super I, O> f) {
		this.f = f;
	}

	@Override
	public Function<? super I, O> func() {
		return f;
	}
}
