
package experimental.compgraph;

public class DefaultCompgraphDataflowNode<IN extends CompgraphEdge<I>, I, BODY extends CompgraphDataflowNodeBody<? extends Dataflow<I, ?>, I, O, ? extends Dataflow<O, ?>>, O, OUT extends CompgraphSingleEdge<O>>
	implements CompgraphNode<IN, BODY, OUT>
{

	private final IN in;
	private final BODY body;
	private OUT out;

	// TODO: this could be a SciJava parameter.
	private final CompgraphNodeFactory factory;

	public DefaultCompgraphDataflowNode(final IN in, final BODY body, final CompgraphNodeFactory factory) {
		this.in = in;
		this.body = body;
		this.factory = factory;
	}

	@Override
	public IN in() {
		return in;
	}

	@Override
	public BODY body() {
		return body;
	}

	@Override
	public OUT out() {
		return out;
	}

	@Override
	public void setOutput(final OUT out) {
		this.out = out;
	}

	@Override
	public CompgraphNodeFactory factory() {
		return factory;
	}
}
