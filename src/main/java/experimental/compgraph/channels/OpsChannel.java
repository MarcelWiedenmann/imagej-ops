package experimental.compgraph.channels;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

//TODO naming of interface
public interface OpsChannel<I> {

	<O> OpsChannel<O> map(Function<? super I, O> func);

	OpsChannel<I> filter(Predicate<? super I> func);

	<O> OpsChannel<O> map(BiConsumer<? super I, Consumer<O>> f);
}
