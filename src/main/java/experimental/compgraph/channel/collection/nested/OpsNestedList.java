
package experimental.compgraph.channel.collection.nested;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import experimental.compgraph.channel.OpsChannel;
import experimental.compgraph.channel.collection.OpsList;

public interface OpsNestedList<I, C extends OpsChannel<I>> extends OpsNestedGrid<I, C>, OpsList<C> {

	@Override
	OpsList<I> treeReduceEach(BiFunction<? super I, ? super I, I> f);

	@Override
	<O> OpsList<O> reduceEach(O memo, BiFunction<O, ? super I, O> f, BiFunction<? super O, ? super O, O> merge);

	@Override
	<O, CC extends OpsChannel<O>> OpsNestedList<O, CC> mapEach(BiConsumer<? super I, Consumer<O>> f);

	@Override
	<O, CC extends OpsChannel<O>> OpsNestedList<O, CC> mapEach(Function<? super I, O> f);
}
