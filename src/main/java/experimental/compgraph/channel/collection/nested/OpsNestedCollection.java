
package experimental.compgraph.channel.collection.nested;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import experimental.compgraph.channel.OpsChannel;
import experimental.compgraph.channel.collection.OpsCollection;

public interface OpsNestedCollection<I, C extends OpsChannel<I>> extends OpsNestedChannel<I, C>, OpsCollection<C> {

	OpsCollection<I> flatten();

	OpsCollection<I> treeReduceEach(BiFunction<? super I, ? super I, I> f);

	<O> OpsCollection<O> reduceEach(O memo, BiFunction<O, ? super I, O> f, BiFunction<? super O, ? super O, O> merge);

	@Override
	<O, CC extends OpsChannel<O>> OpsNestedCollection<O, CC> mapEach(BiConsumer<? super I, Consumer<O>> f);

	@Override
	<O, CC extends OpsChannel<O>> OpsNestedCollection<O, CC> mapEach(Function<? super I, O> f);

	@Override
	default <O> OpsCollection<O> map(final Function<? super C, O> func) {
		// TODO Auto-generated method stub
		return null;
	}
}
