
package experimental.compgraph.interfaces;

import net.imagej.ops.special.function.BinaryFunctionOp;
import net.imagej.ops.special.function.UnaryFunctionOp;
import net.imglib2.util.Pair;

import experimental.compgraph.interfaces.ComputationGraphNode.Input;

// TODO: == implement java.util.stream.BaseStream (or on execution level) https://docs.oracle.com/javase/8/docs/api/java/util/stream/BaseStream.html ==

public interface ComputationGraphNode<I extends Input<?>, O> {

	I in();

	// NB: See http://spark.apache.org/docs/latest/api/java/org/apache/spark/api/java/JavaRDD.html for corresponding Spark
	// functionality & naming.

	// TODO: Accept Java 8 Functional Interfaces as soon as they're implemented in ops.
	// TODO: Restrict aggregate methods to "AggregateOp" input?

	<OO> ComputationGraphNode<UnaryStage<I, O>, OO> map(final UnaryFunctionOp<O, OO> f);

	<OO> ComputationGraphNode<UnaryStage<I, O>, OO> flatAggregate(final BinaryFunctionOp<O, O, OO> f);

	<OO> ComputationGraphNode<UnaryStage<I, O>, OO> treeAggregate(final BinaryFunctionOp<O, O, OO> f);

	ComputationGraphForkNode<UnaryStage<I, O>, O> fork();

	<I2 extends Input<?>, O2, OO> ComputationGraphNode<BinaryStage<I, O, I2, O2>, OO> join(
		ComputationGraphNode<I2, O2> node, final BinaryFunctionOp<O, O2, OO> f);

	// split, collect, ...

	ComputationGraphNode<I, O> copy();

	// -- EXPERIMENTAL INTERFACES --

	// --A--B--C
	// <A,B> ==> <<A, B>,C> == <A,C>

	public interface UnaryStage<A extends Input<?>, B> extends Stage<A, B>, UnaryInput<B> {

		@Override
		ComputationGraphNode<A, B> source();
	}

	public interface BinaryStage<A1 extends Input<?>, B1, A2 extends Input<?>, B2> extends
		Stage<BinaryInput<A1, A2>, Pair<B1, B2>>, BinaryInput<B1, B2>
	{

		@Override
		UnaryStage<A1, B1> first();

		@Override
		UnaryStage<A2, B2> second();

		@Override
		default ComputationGraphNode<A1, B1> firstSource() {
			return first().source();
		}

		@Override
		default ComputationGraphNode<A2, B2> secondSource() {
			return second().source();
		}
	}

	public interface Stage<A extends Input<?>, B> extends Input<B> {
		// NB: Marker interface.
	}

	public interface UnaryInput<E> extends Input<E> {

		ComputationGraphNode<?, E> source();
	}

	public interface BinaryInput<E1, E2> extends Input<Pair<E1, E2>> {

		UnaryInput<E1> first();

		UnaryInput<E2> second();

		default ComputationGraphNode<?, E1> firstSource() {
			return first().source();
		}

		default ComputationGraphNode<?, E2> secondSource() {
			return second().source();
		}
	}

	public interface Input<E> {
		// NB: Marker interface.

		// TODO: String getName() -- this could be useful for named inputs in a graph/module
	}

	public interface ComputationGraphJoinNode<I1, I2, O> extends ComputationGraphNode<BinaryInput<I1, I2>, O> {
		// TODO
	}

	public interface ComputationGraphForkNode<I extends Input<?>, O> {

		ComputationGraphNode<I, O> first();

		ComputationGraphNode<I, O> second();
	}
}
