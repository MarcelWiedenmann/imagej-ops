
package experimental.compgraph.channel.collection.nested;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import experimental.compgraph.channel.OpsChannel;

public interface OpsNestedChannel<I, C extends OpsChannel<I>> extends OpsChannel<C> {

	<O, CC extends OpsChannel<O>> OpsNestedChannel<O, CC> mapEach(Function<? super I, O> f);

	<O, CC extends OpsChannel<O>> OpsNestedChannel<O, CC> filterEach(Predicate<? super I> f);

	<O, CC extends OpsChannel<O>> OpsNestedChannel<O, CC> mapEach(BiConsumer<? super I, Consumer<O>> f);
}
