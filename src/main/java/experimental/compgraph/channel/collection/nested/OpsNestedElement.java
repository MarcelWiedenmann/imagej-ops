
package experimental.compgraph.channel.collection.nested;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import experimental.compgraph.channel.OpsChannel;
import experimental.compgraph.channel.collection.OpsElement;

public interface OpsNestedElement<I, C extends OpsChannel<I>> extends OpsNestedList<I, C>, OpsElement<C> {

	@Override
	<O, CC extends OpsChannel<O>> OpsNestedElement<O, CC> mapEach(BiConsumer<? super I, Consumer<O>> f);

	@Override
	<O, CC extends OpsChannel<O>> OpsNestedElement<O, CC> mapEach(Function<? super I, O> f);

	@Override
	<O> OpsElement<O> reduceEach(O memo, BiFunction<O, ? super I, O> f, BiFunction<? super O, ? super O, O> merge);

	@Override
	OpsElement<I> treeReduceEach(BiFunction<? super I, ? super I, I> f);

}
