
package experimental.compgraph;

import net.imagej.ops.special.function.BinaryFunctionOp;
import net.imagej.ops.special.function.UnaryFunctionOp;
import net.imglib2.util.Pair;

// NB: See http://spark.apache.org/docs/latest/api/java/org/apache/spark/api/java/JavaRDD.html for corresponding Spark
// functionality & naming.
// TODO: implement java.util.stream.BaseStream (or on execution level)?: https://docs.oracle.com/javase/8/docs/api/java/util/stream/BaseStream.html
// TODO: Accept Java 8 Functional Interfaces as soon as they're implemented in ops.
// TODO: meta nodes
// TODO: keys & split, collect, ...
public interface ComputationGraphNode<I extends Input<?>, O> extends Stage<I, O>, UnaryInput<O> {

	I in();

	UnaryFunctionOp<?, O> func();

	@Override
	default ComputationGraphNode<I, O> source() {
		return this;
	}

	<OO> ComputationGraphMapNode<O, OO> append(UnaryFunctionOp<O, OO> f);

	<O2, OO> ComputationGraphJoinNode<O, O2, OO> joinFirst(ComputationGraphNode<?, O2> node,
		BinaryFunctionOp<O, O2, OO> f);

	<O2, OO> ComputationGraphJoinNode<O2, O, OO> joinSecond(ComputationGraphNode<?, O2> node,
		BinaryFunctionOp<O2, O, OO> f);

	ComputationGraphForkNode<I, O> fork();

	ComputationGraphNode<I, O> copy();

	// -- Convenience Nodes --

	public interface ComputationGraphJoinNode<I1, I2, O> extends ComputationGraphNode<BinaryInput<I1, I2>, O> {

		@Override
		UnaryFunctionOp<Pair<I1, I2>, O> func();
	}

	public interface ComputationGraphForkNode<I extends Input<?>, O> {

		ComputationGraphNode<I, O> first();

		ComputationGraphNode<I, O> second();
	}

	public interface ComputationGraphMapNode<I, O> extends ComputationGraphNode<UnaryInput<I>, O> {

		@Override
		UnaryFunctionOp<I, O> func();
	}

	public interface ComputationGraphAggregateNode<I, O> extends ComputationGraphNode<BinaryInput<I, I>, O> {

		@Override
		UnaryFunctionOp<Pair<I, I>, O> func();
	}
}
