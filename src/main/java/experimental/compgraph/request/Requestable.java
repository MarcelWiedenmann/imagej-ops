
package experimental.compgraph.request;

public interface Requestable<K, R extends Request<K>, V> {

	// NB: List<V> and List<R> have to be ordered such that V and R belonging
	// together are located at the same index.
	V request(R request);
}
