
package experimental.compgraph.body;

import java.util.function.Function;
import java.util.stream.Stream;

import experimental.compgraph.CompgraphEdge;
import experimental.compgraph.CompgraphSingleEdge;
import experimental.compgraph.DefaultDataflow;

public class DefaultMap<IN extends CompgraphEdge<I>, I, O, OUT extends CompgraphSingleEdge<O>> implements
	Map<IN, I, O, OUT>
{

	private final Function<? super I, O> f;

	public DefaultMap(final Function<? super I, O> f) {
		this.f = f;
	}

	// -- [Testdriven:] --
	// TODO: something like this... (maybe on node level, etc etc...don't know yet where 'in' comes from etc.
	// (But in general this 'dataflow'-approach seems to work out.)
	// TODO: make in.dataflow().inner() type-safe somehow? boxing is uhugly :X (making it typesafe, however, pollutes our
	// edges' class signatures...we _certainly_ don't want this as edges don't know anything about the data flow they
	// convey - we have to trust the factory/service that matched data flow and body...).
	public DefaultDataflow<O> apply() {
		final CompgraphSingleEdge<I> in = null;
		if (in.dataflow().inner() instanceof Stream<?>) {
			return new DefaultDataflow<>(((Stream<I>) in.dataflow().inner()).map(f));
		}
		throw new UnsupportedOperationException("The default implementation of Map requires java.util.Stream as input.");
	}
	// ----

	@Override
	public Function<? super I, O> func() {
		return f;
	}
}
