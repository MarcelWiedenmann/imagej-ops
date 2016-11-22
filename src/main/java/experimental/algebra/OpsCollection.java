
package experimental.algebra;

import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

import net.imagej.ops.special.function.BinaryFunctionOp;
import net.imagej.ops.special.function.UnaryFunctionOp;
import net.imglib2.util.Pair;

import experimental.compgraph.Fork;

public interface OpsCollection<I> {

	// -- First Order Operations --

	<O> OpsCollection<O> map(UnaryFunctionOp<I, O> f);

	<O> OpsCollection<O> reduce(BinaryFunctionOp<O, I, O> f);

	<O> OpsCollection<O> aggregate(BinaryFunctionOp<O, I, O> f);

	OpsCollection<I> concat(OpsCollection<I> c);

	Fork<? extends OpsCollection<I>> fork();

	<I2> OpsCollection<Pair<I, I2>> join(OpsCollection<I2> c, BiPredicate<I, I2> f);

	OpsCollection<I> filter(Predicate<I> f);

	<I2> OpsCollection<Pair<I, I2>> cartesian();

	OpsList<I> sort(Comparator<I> f);

	OpsBoundedStream<I> stream();

	// -- Higher Order Operators --

	// TODO: Nested collections? coll-map and merge should be added there.
	// TODO: How to determine O in scatter & scatterElements? Allow converters? Identity?

	<O> OpsCollection<? extends OpsCollection<O>> scatter(/*..*/);

	<O> OpsCollection<? extends OpsHandle<O>> scatterElements();

	<O> OpsCollection<? extends OpsCollection<O>> partition(UnaryFunctionOp<I, OpsCollection<O>> f);
}
