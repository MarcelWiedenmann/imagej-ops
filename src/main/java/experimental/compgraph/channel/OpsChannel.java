
package experimental.compgraph.channel;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import experimental.compgraph.CompgraphSingleEdge;

public interface OpsChannel<I> extends CompgraphSingleEdge<I> {

	default <O, OUT extends OpsChannel<O>> OUT transform(final Function<? super OpsChannel<I>, OUT> f) {
		return f.apply(this);
	}

	<O> OpsChannel<O> map(Function<? super I, O> f);

	<O> OpsChannel<O> map(BiConsumer<? super I, ? extends Consumer<O>> f);

	OpsChannel<I> filter(Predicate<? super I> f);

	<O> OpsChannel<? extends OpsBoundedChannel<O>> partition(BiConsumer<? super I, ? extends Consumer<O>> f);

	<O> OpsChannel<? extends OpsChannel<O>> group(Function<? super I, Integer> f);
}
