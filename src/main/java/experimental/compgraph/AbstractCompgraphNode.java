
package experimental.compgraph;

public abstract class AbstractCompgraphNode<IN extends CompgraphEdge<?>, BODY extends CompgraphNodeBody<? super IN, OUT>, OUT extends CompgraphSingleEdge<?>>
	implements CompgraphNode<IN, BODY, OUT>
{

	private final IN in;
	private final BODY body;
	private OUT out;

	public AbstractCompgraphNode(final IN in, final BODY body) {
		this.in = in;
		this.body = body;
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
}
