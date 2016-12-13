
package experimental.compgraph.request;

public interface Requestable<K, R extends Request<K>, V> {

	V request(Iterable<R> requests);
}
