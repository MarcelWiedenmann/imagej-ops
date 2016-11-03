
package experimental.tiling;

import java.util.Arrays;
import java.util.List;

import net.imagej.ops.AbstractOpTest;
import net.imagej.ops.special.function.AbstractBinaryFunctionOp;
import net.imagej.ops.special.function.AbstractUnaryFunctionOp;
import net.imagej.ops.special.function.BinaryFunctionOp;
import net.imglib2.util.Pair;

import org.junit.Test;

import experimental.compgraph.implementations.ComputationGraphFactory;
import experimental.compgraph.interfaces.ComputationGraph;
import experimental.compgraph.interfaces.ComputationGraphNode;
import experimental.compgraph.interfaces.ComputationGraphNode.BinaryStage;
import experimental.compgraph.interfaces.ComputationGraphNode.ComputationGraphJoinNode;
import experimental.compgraph.interfaces.ComputationGraphNode.Input;
import experimental.compgraph.interfaces.ComputationGraphNode.UnaryInput;
import experimental.compgraph.interfaces.ComputationGraphNode.UnaryStage;
import experimental.tiling.mapreduce.BinaryDistributable;
import experimental.tiling.mapreduce.BinaryMappable;
import experimental.tiling.mapreduce.BinaryTilingNode;
import experimental.tiling.mapreduce.UnaryMappable;

public class TilingPlanTest extends AbstractOpTest {

	@Test
	public void test() {

		final Integer[] tiledInput1 = { 0, 1, 2, 3, 4, 5 };
		final Integer[] tiledInput2 = { 0, 1, 2, 3, 4, 5 };

		final ComputationGraphFactory cgfactory = new ComputationGraphFactory();

		// out = in + 1
		final ComputationGraph<Integer, Integer> cg1 = cgfactory.create(new IncrementFunctionOp());

		// out = (in1 + 1 + in2) + (in2 + 1) as String
		final ComputationGraph<Pair<Integer, Integer>, String> cg2 = cgfactory.create(new MyVeryComplexProcessing())
			.joinSecond(cg1, new SumPairsFunctionOp()).append(new ToStrFunctionOp());

		final Tiling<Pair<Integer, Integer>, Integer> t = tilingService.getTilingPlan(cg2);

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
		public ComputationGraphJoinNode<UnaryStage<?, Integer>, UnaryStage<?, Integer>, Integer> getDistributionPlan(
			final BinaryTilingNode<Integer, Integer> t)
		{
			// FIXME
			final ComputationGraphNode<Input<?>, ?> result = t.first().map(new IncrementFunctionOp()).join(t.second(),
				new SumPairsFunctionOp());
			throw new UnsupportedOperationException("not yet implemented");
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
