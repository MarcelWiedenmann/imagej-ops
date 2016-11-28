
package experimental.compgraph;

public class DefaultCompgraphNode<IN extends CompgraphEdge<?>, BODY extends CompgraphNodeBody<? super IN, OUT>, OUT extends CompgraphSingleEdge<?>>
	extends AbstractCompgraphNode<IN, BODY, OUT>
{

	public DefaultCompgraphNode(final IN in, final BODY body) {
		super(in, body);
	}
}
