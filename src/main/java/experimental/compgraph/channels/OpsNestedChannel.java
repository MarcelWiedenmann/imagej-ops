
package experimental.compgraph.channels;

public interface OpsNestedChannel<T, C extends OpsChannel<T>> extends OpsChannel<C> {

	OpsCollection<T> flatten();
}
