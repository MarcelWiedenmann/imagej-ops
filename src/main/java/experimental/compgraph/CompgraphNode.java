
package experimental.compgraph;

// TODO: introduce 'I' and 'O' in class signature? (or remove it from CompgraphNodeBody's signature)
public interface CompgraphNode<IN extends CompgraphEdge<?>, BODY extends CompgraphNodeBody<?, ?, ?, ?>, OUT extends CompgraphSingleEdge<?>> {

	CompgraphNodeFactory factory();

	IN in();

	BODY body();

	OUT out();

	void setOutput(OUT out);
}
