
package experimental.compgraph.request;

public interface Requests<R extends Request> extends Iterable<R> {
	// NB: Marker interface.

	<V extends Request> Requests<V> merge(Requests<R> r);
}
