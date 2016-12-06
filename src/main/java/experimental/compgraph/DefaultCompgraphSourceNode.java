
package experimental.compgraph;

import java.util.Arrays;
import java.util.Collection;

import experimental.compgraph.body.DefaultSource;
import experimental.compgraph.channel.collection.DefaultOpsCollection;

public class DefaultCompgraphSourceNode<I> implements
	CompgraphNode<CompgraphEdge<I>, DefaultSource<I>, DefaultOpsCollection<I>>
{

	Collection<I> input;
	DefaultSource<I> body;
	DefaultOpsCollection<I> out;

	CompgraphNodeFactory factory;

	public DefaultCompgraphSourceNode(final I... input) {
		this.input = Arrays.asList(input);
		this.body = new DefaultSource<>();
		this.factory = new CompgraphNodeFactory();
	}

	public Collection<I> get() {
		return input;
	}

	@Override
	public CompgraphNodeFactory factory() {
		return factory;
	}

	// TODO: we need an interface for source nodes as they have no input
	@Override
	public CompgraphEdge<I> in() {
		return null;
	}

	@Override
	public DefaultSource<I> body() {
		return body;
	}

	@Override
	public DefaultOpsCollection<I> out() {
		return out;
	}

	@Override
	public void setOutput(final DefaultOpsCollection<I> out) {
		this.out = out;
	}
}
