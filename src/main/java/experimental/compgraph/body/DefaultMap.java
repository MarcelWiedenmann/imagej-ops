
package experimental.compgraph.body;

import java.util.function.Function;

import experimental.compgraph.DefaultDataflow;

public class DefaultMap<IN extends DefaultDataflow<I>, I, O, OUT extends DefaultDataflow<O>> implements
	Map<IN, I, O, OUT>
{

	private final Function<? super I, O> f;

	public DefaultMap(final Function<? super I, O> f) {
		this.f = f;
	}

	// -- [Testdriven:] --
	// TODO: something like this... (maybe on node level, etc etc...don't know yet where 'in' comes from etc.
	// (But in general this 'dataflow'-approach seems to work out.)
	public DefaultDataflow<O> apply() {
		final DefaultDataflow<I> in = null;

		return new DefaultDataflow<>(in.inner().map(f));
	}

	// -- Map --

	@Override
	public Function<? super I, O> func() {
		return f;
	}
}
