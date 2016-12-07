
package experimental.compgraph.service;

import java.util.function.Function;

import experimental.compgraph.CompgraphSingleEdge;
import experimental.compgraph.DataHandle;
import experimental.compgraph.node.LocalMap;
import experimental.compgraph.node.Map;

public class LocalCompgraphNodeFactory implements CompgraphNodeFactory {

	@Override
	public <IN extends CompgraphSingleEdge<I>, I, O> Map<I, ? extends DataHandle<I, ?>, O, ? extends DataHandle<O, ?>>
		map(final IN in, final Function<? super I, O> f)
	{
		// TODO: We need some Ops-like matching to match interfaces such as 'Map' with their respective implementations
		// given the target execution environment ('DefaultMap' for local execution).

		final Map<I, ? extends DataHandle<I, ?>, O, ? extends DataHandle<O, ?>> map = new LocalMap<>(f, in);
		in.parent().context().inject(map);
		return map;
	}
}
