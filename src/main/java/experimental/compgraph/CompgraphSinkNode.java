
package experimental.compgraph;

public interface CompgraphSinkNode<I, IN extends DataHandle<I, ?>, OUT> extends CompgraphInputNode<I, IN> {

	OUT get();
}
