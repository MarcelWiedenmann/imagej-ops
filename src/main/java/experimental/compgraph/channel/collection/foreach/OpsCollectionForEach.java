
package experimental.compgraph.channel.collection.foreach;

import experimental.compgraph.channel.collection.OpsCollection;
import experimental.compgraph.channel.collection.nested.OpsNestedCollection;

public interface OpsCollectionForEach<T, CF extends OpsCollectionForEach<T, CF>> extends OpsCollection<T> {

	CF createFromContainer(final OpsNestedCollection<T, ? extends OpsCollection<T>> container);
}
