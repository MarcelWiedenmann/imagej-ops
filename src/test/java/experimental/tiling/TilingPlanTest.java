
package experimental.tiling;

import java.util.Arrays;
import java.util.List;

import net.imagej.ops.AbstractOpTest;
import net.imagej.ops.special.function.AbstractBinaryFunctionOp;
import net.imagej.ops.special.function.AbstractUnaryFunctionOp;
import net.imagej.ops.special.function.BinaryFunctionOp;
import net.imglib2.util.Pair;

import org.junit.Test;

import experimental.compgraph.BinaryInput;
import experimental.compgraph.BinaryStage;
import experimental.compgraph.ComputationGraph;
import experimental.compgraph.ComputationGraphNode;
import experimental.compgraph.ComputationGraphNode.ComputationGraphJoinNode;
import experimental.compgraph.ComputationGraphNode.ComputationGraphMapNode;
import experimental.compgraph.Input;
import experimental.compgraph.UnaryInput;
import experimental.compgraph.UnaryStage;
import experimental.compgraph_old.ComputationGraphFactory;
import experimental.tiling.mapreduce.BinaryDistributable;
import experimental.tiling.mapreduce.BinaryMappable;
import experimental.tiling.mapreduce.BinaryTilingNode;
import experimental.tiling.mapreduce.UnaryMappable;

public class TilingPlanTest extends AbstractOpTest {

	@Test
	public void test() {

		final Integer[] tiledInput1 = { 0, 1, 2, 3, 4, 5 };
		final Integer[] tiledInput2 = { 0, 1, 2, 3, 4, 5 };


		final TilingService ts;
		ts.create().append(new IncrementFunctionOp()).append(new MyVeryComplexProcessing().fork()

		// --

		final ComputationGraphNode<UnaryInput<Integer>, Integer> n1;
		final ComputationGraphNode<UnaryInput<Integer>, Integer> n2;
		final ComputationGraphMapNode<Integer, Integer> map = n1.append(new IncrementFunctionOp());
		final ComputationGraphMapNode<Integer, Integer> append = map.append(new MyVeryComplexProcessing());
		final ComputationGraphJoinNode<Integer, Integer, Integer> join = append.joinSecond(n2, new SumPairsFunctionOp());
		final ComputationGraphMapNode<Integer, String> append2 = join.append(new ToStrFunctionOp<Integer>());

		final ComputationGraph<UnaryInput<Integer>, Integer> g1;
		final ComputationGraph<UnaryInput<Integer>, Integer> g2;
		final ComputationGraph<UnaryInput<Integer>, Integer> gmap = g1.append(new IncrementFunctionOp());
		final ComputationGraph<UnaryInput<Integer>, Integer> gappend = gmap.append(new MyVeryComplexProcessing());
		final ComputationGraph<BinaryInput<UnaryInput<Integer>, UnaryInput<Integer>>, Integer> gjoin = gappend.joinSecond(
			g2, new SumPairsFunctionOp());
		final ComputationGraph<BinaryInput<UnaryInput<Integer>, UnaryInput<Integer>>, String> gappend2 = gjoin.append(
			new ToStrFunctionOp<Integer>());
		final ComputationGraph<BinaryInput<BinaryInput<UnaryInput<Integer>, UnaryInput<Integer>>, UnaryInput<Integer>>, Integer> gjoin2 =
			gjoin.joinFirst(g2, new SumPairsFunctionOp());

		// --

		final ComputationGraphFactory cgfactory = new ComputationGraphFactory();

		// out = in + 1
		final DistributedGrid<Integer, Integer> t1;
		t1 = t1.append(new IncrementFunctionOp());

		final DistributedGrid<String> t2;
		t2 = t2.append(new MyVeryComplexProcessing()).joinSecond(t1, new SumPairsFunctionOp()).append(
			new ToStrFunctionOp<>());

		final ComputationGraph<Integer, Integer> cg1 = cgfactory.create(new IncrementFunctionOp());

		// out = (in1 + 1 + in2) + (in2 + 1) as String
		final ComputationGraph<Pair<Integer, Integer>, String> cg2 = cgfactory.create(new MyVeryComplexProcessing())
			.joinSecond(cg1, new SumPairsFunctionOp()).append(new ToStrFunctionOp());

		final BinaryFunctionOp<List<Integer>, List<Integer>, String> op = tilingService.getExecutable(t,
			new LocalExecutionPlanner());

		op.compute(Arrays.asList(tiledInput1), Arrays.asList(tiledInput2));

		// --

		final ComputationGraphNode<UnaryInput<Integer>, Integer> node1;
		final ComputationGraphNode<UnaryInput<Integer>, Integer> node2;
		final ComputationGraphNode<UnaryInput<Integer>, Integer> node3;

		// Es ist...wunderschön :')

		final ComputationGraphNode<UnaryStage<UnaryStage<BinaryStage<BinaryStage<UnaryInput<Integer>, Integer, UnaryInput<Integer>, Integer>, Integer, UnaryInput<Integer>, Integer>, Integer>, Integer>, String> leaf =

			node1.join(node2, new MyVeryComplexProcessing()).join(node3, new SumPairsFunctionOp()).map(
				new IncrementFunctionOp()).map(new ToStrFunctionOp<>());

		leaf.in().source().in().source().in().firstSource().in().firstSource().in(); // = input-handle von node1

		// --
	}

	public static class MyVeryComplexProcessing extends AbstractBinaryFunctionOp<Integer, Integer, Integer> implements
		BinaryDistributable<Integer, Integer, Integer>
	{

		@Override
		public DistributedCollection<? extends Pair<?, ?>, Integer> getDistributionPlan(
			final JoinedDistributedCollection<?, ?, Integer, Integer> c)
		{
			c.first().map(f)
		}

		@Override
		public ComputationGraphJoinNode<UnaryStage<?, Integer>, UnaryStage<?, Integer>, Integer> getDistributionPlan(
			final BinaryTilingNode<Integer, Integer> t)
		{

			final ComputationGraphNode<UnaryInput<?>, Integer> first = t.first();
			final ComputationGraphNode<UnaryInput<?>, Integer> second = t.second();

			final ComputationGraphNode<UnaryStage<UnaryInput<?>, Integer>, Integer> third = first.map(
				new IncrementFunctionOp());

			// This MUST work by definition!: (and does by using "? extends ...")
			final ComputationGraphNode<? extends Input<?>, Integer> thirdSimplerType = third;

			final ComputationGraphNode<BinaryStage<UnaryStage<UnaryInput<?>, Integer>, Integer, UnaryInput<?>, Integer>, Integer> fourth =

				third.join(t.second(), new SumPairsFunctionOp());

			// This MUST work by definition!: (and does by using "? extends ...")
			final ComputationGraphNode<? extends BinaryInput<Integer, Integer>, Integer> fourthSimplerType = fourth;

			// FIXME
			// final ComputationGraphNode<Input<?>, ?> result =
			final ComputationGraphNode<BinaryStage<UnaryStage<UnaryInput<?>, Integer>, Integer, UnaryInput<?>, Integer>, Integer> result =
				t.first().map(new IncrementFunctionOp()).join(t.second(), new SumPairsFunctionOp());

			// TODO:
			// return type is:
			// ComputationGraphJoinNode<UnaryStage<?, Integer>, UnaryStage<?, Integer>, Integer>
			// ComputationGraphNode<BinaryInput<UnaryStage<?, Integer>, UnaryStage<?, Integer>>, Integer>
			// should be simplified to sth like:
			// ComputationGrapNode<? extends BinaryInput<Integer, Integer>, Integer> =
			// ComputationGraphJoinNode<Integer,Integer,Integer> (<-- should be perfect for the user, make sure that type info
			// does not get lost on system side...) or
			// ComputationGraphJoinNode<? extends UnaryInput<Integer>, ? extends UnaryInput<Integer>, Integer> or pairs etc..

			throw new UnsupportedOperationException("not yet implemented");

			// return SomeJoinNode<Integer,Integer, Integer> (which should implicitly keep the nested generics of the
			// underlying graph, hide from user, show system - how? we probably end up casting and using system-internal type
			// knowledge...).
		}

		@Override
		public Integer compute2(final Integer input1, final Integer input2) {
			return input1 + 1 + input2;
		}
	}

	public static class IncrementFunctionOp extends AbstractUnaryFunctionOp<Integer, Integer> implements
		UnaryMappable<Integer, Integer>
	{

		@Override
		public Integer compute1(final Integer input) {
			return input + 1;
		}
	}

	public static class SumPairsFunctionOp extends AbstractBinaryFunctionOp<Integer, Integer, Integer> implements
		BinaryMappable<Integer, Integer, Integer>
	{

		@Override
		public Integer compute2(final Integer input1, final Integer input2) {
			return input1 + input2;
		}
	}

	public static class ToStrFunctionOp<I> extends AbstractUnaryFunctionOp<I, String> implements
		UnaryMappable<I, String>
	{

		@Override
		public String compute1(final I input) {
			final String output = input.toString();
			System.out.println(output);
			return output;
		}
	}
}
