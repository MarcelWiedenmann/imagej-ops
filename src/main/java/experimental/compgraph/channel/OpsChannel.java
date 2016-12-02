
package experimental.compgraph.channel;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public interface OpsChannel<I> {

	<O> OpsChannel<O> transform(Function<? super OpsChannel<I>, OpsChannel<O>> f);

	<O> OpsChannel<O> map(Function<? super I, O> f);

	<O> OpsChannel<O> map(BiConsumer<? super I, ? extends Consumer<O>> f);

	OpsChannel<I> filter(Predicate<? super I> f);

	<O> OpsChannel<? extends OpsBoundedChannel<O>> partition(Function<? super I, O> f);

	<O> OpsChannel<? extends OpsBoundedChannel<O>> group(Function<? super I, Integer> f);
}
