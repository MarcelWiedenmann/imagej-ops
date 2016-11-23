
package experimental.algebra;

import java.util.Comparator;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import net.imglib2.util.Pair;

import experimental.compgraph.Fork;
import experimental.compgraph.UnaryEdge;

public interface OpsCollection<I> extends UnaryEdge<I> {

	// -- First Order Operations --
	<O> OpsCollection<O> map(Function<? super I, O> f);

	// which is actually a UnaryComputerOp in ops
	<O> OpsCollection<O> map(BiConsumer<I, Consumer<O>> f);

	<O> OpsCollection<O> reduce(O memo, BiFunction<O, I, O> f);

	<O, K> OpsCollection<O> aggregate(BiFunction<O, I, O> f);

	OpsCollection<I> concat(OpsCollection<I> c);

	Fork<? extends OpsCollection<I>> fork();

	<I2> OpsCollection<Pair<I, I2>> join(OpsCollection<I2> c, BiPredicate<I, I2> f);

	OpsCollection<I> filter(Predicate<I> f);

	<I2> OpsCollection<Pair<I, I2>> cartesian(OpsCollection<I2> coll);

	OpsList<I> sort(Comparator<I> f);

	OpsBoundedStream<I> stream();

	// -- Higher Order Operators --

	// TODO: Nested collections? coll-map and merge should be added there.
	// TODO: How to determine O in scatter & scatterElements? Allow converters?
	// Identity?

	<O, C extends OpsCollection<O>> OpsCollectionNested<O, C> scatter(Function<I, Integer> func);

	<O, C extends OpsCollection<O>> OpsCollectionNested<O, C> partition(Function<I, OpsCollection<O>> f);
}
