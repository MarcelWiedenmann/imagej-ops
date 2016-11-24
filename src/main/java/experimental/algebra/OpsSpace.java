package experimental.algebra;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import experimental.compgraph.UnaryEdge;

//TODO naming of interface
public interface OpsSpace<I> {
	<O> UnaryEdge<O> map(Function<? super I, O> func);

	UnaryEdge<I> filter(Predicate<? super I> func);

	<O> UnaryEdge<O> map(BiConsumer<? super I, Consumer<O>> f);
}
