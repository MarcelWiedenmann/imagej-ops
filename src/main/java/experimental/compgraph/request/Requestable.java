
package experimental.compgraph.request;

import experimental.compgraph.DataHandle;

public interface Requestable<R extends Request, V> extends DataHandle<V, Requestable<R, V>> {

	V request(Requests<R> r);
}
