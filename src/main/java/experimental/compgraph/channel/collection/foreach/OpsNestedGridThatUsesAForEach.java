
package experimental.compgraph.channel.collection.foreach;

import experimental.compgraph.channel.collection.OpsCollection;
import experimental.compgraph.channel.collection.nested.OpsNestedGrid;

public interface OpsNestedGridThatUsesAForEach<T, C extends OpsCollection<T>, CF extends OpsCollectionForEach<T, CF>>
	extends OpsNestedGrid<T, C>
{

	CF forEach();

	<O, CO extends OpsCollection<O>, CFO extends OpsCollectionForEach<O, CFO>> OpsNestedGridThatUsesAForEach<O, CO, CFO>
		createFromForEach(CFO innerType);
}
