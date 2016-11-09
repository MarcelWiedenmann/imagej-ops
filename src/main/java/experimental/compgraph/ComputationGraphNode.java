
package experimental.compgraph;

import net.imagej.ops.special.function.BinaryFunctionOp;
import net.imagej.ops.special.function.UnaryFunctionOp;
import net.imglib2.util.Pair;

// TODO: == implement java.util.stream.BaseStream (or on execution level) https://docs.oracle.com/javase/8/docs/api/java/util/stream/BaseStream.html ==

public interface ComputationGraphNode<I extends Input<?>, O> extends Stage<I, O> {

	I in();

	UnaryFunctionOp<?, O> func();

	// NB: See http://spark.apache.org/docs/latest/api/java/org/apache/spark/api/java/JavaRDD.html for corresponding Spark
	// functionality & naming.

	// TODO: Accept Java 8 Functional Interfaces as soon as they're implemented in ops.
	// TODO: Restrict aggregate methods to "AggregateOp" input? (...would be un-Spark-ish but may improve API)

	// -- Graph Operations --

	<OO> ComputationGraphNode<UnaryStage<I, O>, OO> append(UnaryFunctionOp<O, OO> f);

	<I2 extends Input<?>, O2, OO> ComputationGraphNode<BinaryStage<I, O, I2, O2>, OO> joinFirst(
		ComputationGraphNode<I2, O2> node, final BinaryFunctionOp<O, O2, OO> f);

	<I2 extends Input<?>, O2, OO> ComputationGraphNode<BinaryStage<I, O, I2, O2>, OO> joinSecond(
		ComputationGraphNode<I2, O2> node, final BinaryFunctionOp<O, O2, OO> f);

	// -- MapReduce Operations --

	<OO> ComputationGraphNode<UnaryStage<I, O>, OO> map(final UnaryFunctionOp<O, OO> f);

	<OO> ComputationGraphNode<UnaryStage<I, O>, OO> flatAggregate(final BinaryFunctionOp<O, O, OO> f);

	<OO> ComputationGraphNode<UnaryStage<I, O>, OO> treeAggregate(final BinaryFunctionOp<O, O, OO> f);

	ComputationGraphForkNode<UnaryStage<I, O>, O> fork();

	// split, collect, ...

	ComputationGraphNode<I, O> copy();

	// -- Special Nodes --

	public interface ComputationGraphJoinNode<I1, I2, I extends BinaryInput<I1, I2>, O> extends
		ComputationGraphNode<I, O>
	{

		@Override
		UnaryFunctionOp<Pair<I1, I2>, O> func();
	}

	public interface ComputationGraphForkNode<I extends Input<?>, O> {

		ComputationGraphNode<I, O> first();

		ComputationGraphNode<I, O> second();
	}
}
