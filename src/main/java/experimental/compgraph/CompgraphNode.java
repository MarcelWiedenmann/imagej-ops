
package experimental.compgraph;

public interface CompgraphNode<IN extends CompgraphEdge<?>, BODY extends CompgraphNodeBody<? super IN, OUT>, OUT extends CompgraphSingleEdge<?>> {

	IN in();

	BODY body();

	OUT out();

	void setOutput(OUT out);
}
