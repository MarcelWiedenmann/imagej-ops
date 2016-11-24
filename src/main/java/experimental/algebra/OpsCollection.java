
package experimental.algebra;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import net.imglib2.util.Pair;

import experimental.compgraph.Fork;
import experimental.compgraph.UnaryEdge;

public interface OpsCollection<I> extends UnaryEdge<I>, OpsSpace<I> {

	// -- First Order Operations --
	@Override
	<O> OpsCollection<O> map(Function<? super I, O> f);

	// which is actually a UnaryComputerOp in ops
	@Override
	<O> OpsCollection<O> map(BiConsumer<? super I, Consumer<O>> f); // f =
																	// Supplier<O>?

	@Override
	OpsCollection<I> filter(Predicate<? super I> f);

	<O> OpsCollection<O> reduce(O memo, BiFunction<O, ? super I, O> f);

	<O, K> OpsCollection<O> aggregate(BiFunction<O, ? super I, O> f);

	OpsCollection<I> concat(OpsCollection<I> c);

	Fork<? extends OpsCollection<I>> fork();

	<I2> OpsCollection<Pair<I, I2>> join(OpsCollection<I2> c, BiPredicate<I, I2> f);

	<I2> OpsCollection<Pair<I, I2>> cartesian(OpsCollection<I2> c);

	OpsList<I> sort(Comparator<I> f);

	OpsBoundedStream<I> stream();

	// -- Higher Order Operators --

	// TODO: Nested collections? coll-map and merge should be added there.
	// TODO: How to determine O in scatter? Allow converters?
	// Identity?

	<C extends OpsCollection<I>> OpsCollection<C> scatter(Function<I, Integer> func);

	// OpsGrid.scatter() actually can be equivalent to OpsGrid.partition(..)

	// pure syntactical sugar
	<O, C extends OpsCollection<O>> OpsCollection<C> partition(final Function<I, List<O>> func);
}
