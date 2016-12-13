
package experimental.compgraph.request;

import java.util.List;

public interface Requestable<K, R extends Request<K>, V> {

	// NB: List<V> and List<R> have to be ordered such that V and R belonging together are located at the same index.
	List<V> request(List<R> requests);
}
