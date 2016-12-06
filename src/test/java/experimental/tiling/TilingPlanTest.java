//
//package experimental.tiling;
//
//import net.imagej.ops.AbstractOpTest;
//import net.imagej.ops.special.function.AbstractBinaryFunctionOp;
//import net.imagej.ops.special.function.AbstractUnaryFunctionOp;
//
//import org.junit.Test;
//
//import experimental.algebra_backup.compgraph.BinaryInput;
//import experimental.algebra_backup.compgraph.UnaryInput;
//import experimental.compgraph.BinaryAsUnaryFunctionOp;
//import experimental.compgraph.ComputationGraph;
//import experimental.compgraph.ComputationGraphNode;
//import experimental.compgraph.ComputationGraphNode.ComputationGraphJoinNode;
//import experimental.compgraph.ComputationGraphNode.ComputationGraphMapNode;
//import experimental.compgraph_old.BinaryStage;
//import experimental.compgraph_old.UnaryStage;
//import experimental.tiling.mapreduce.BinaryDistributable;
//import experimental.tiling.mapreduce.BinaryMappable;
//import experimental.tiling.mapreduce.UnaryMappable;
//
//public class TilingPlanTest extends AbstractOpTest {
//
//	@Test
//	public void test() {
//
//		final Integer[] tiledInput1 = { 0, 1, 2, 3, 4, 5 };
//		final Integer[] tiledInput2 = { 0, 1, 2, 3, 4, 5 };
//
//		final TilingService ts;
//		final ComputationGraphNode<UnaryInput<Integer>, Integer> test1; // = ts.create<..>(..);
//		final ComputationGraphNode<UnaryInput<Integer>, Integer> test2;
//		final ComputationGraphMapNode<Integer, String> graph = test1.join(test1.join(test2, new SumPairsFunctionOp()),
//			new MyVeryComplexProcessing()).append(new IncrementFunctionOp()).append(new ToStrFunctionOp<Integer>());
//
//		// --
//
//		final ComputationGraphNode<UnaryInput<Integer>, Integer> n1;
//		final ComputationGraphNode<UnaryInput<Integer>, Integer> n2;
//		final ComputationGraphMapNode<Integer, Integer> map = n1.append(new IncrementFunctionOp());
//		final ComputationGraphMapNode<Integer, Integer> append = map.append(new MyVeryComplexProcessing());
//		final ComputationGraphJoinNode<Integer, Integer, Integer> join = append.joinSecond(n2, new SumPairsFunctionOp());
//		final ComputationGraphMapNode<Integer, String> append2 = join.append(new ToStrFunctionOp<Integer>());
//
//		final ComputationGraph<UnaryInput<Integer>, Integer> g1;
//		final ComputationGraph<UnaryInput<Integer>, Integer> g2;
//		final ComputationGraph<UnaryInput<Integer>, Integer> gmap = g1.append(new IncrementFunctionOp());
//		final ComputationGraph<UnaryInput<Integer>, Integer> gappend = gmap.append(new MyVeryComplexProcessing());
//		final ComputationGraph<BinaryInput<UnaryInput<Integer>, UnaryInput<Integer>>, Integer> gjoin = gappend.joinSecond(
//			g2, new SumPairsFunctionOp());
//		final ComputationGraph<BinaryInput<UnaryInput<Integer>, UnaryInput<Integer>>, String> gappend2 = gjoin.append(
//			new ToStrFunctionOp<Integer>());
//		final ComputationGraph<BinaryInput<BinaryInput<UnaryInput<Integer>, UnaryInput<Integer>>, UnaryInput<Integer>>, Integer> gjoin2 =
//			gjoin.joinFirst(g2, new SumPairsFunctionOp());
//
//		// --
//
//		// Es ist...wunderschön :')
//
//		final ComputationGraphNode<UnaryStage<UnaryStage<BinaryStage<BinaryStage<UnaryInput<Integer>, Integer, UnaryInput<Integer>, Integer>, Integer, UnaryInput<Integer>, Integer>, Integer>, Integer>, String> leaf =
//
//			node1.join(node2, new MyVeryComplexProcessing()).join(node3, new SumPairsFunctionOp()).map(
//				new IncrementFunctionOp()).map(new ToStrFunctionOp<>());
//
//		leaf.in().source().in().source().in().firstSource().in().firstSource().in(); // = input-handle von node1
//
//		// --
//	}
//
//	// --- TOY OPS ---
//
//	public static class MyVeryComplexProcessing extends AbstractBinaryFunctionOp<Integer, Integer, Integer> implements
//		BinaryDistributable<Integer, Integer, Integer>
//	{
//
//		@Override
//		public DistributedList<Integer> getDistributionPlan(final DistributedList<Integer> in1,
//			final DistributedList<Integer> in2)
//		{
//
//			return in1.map(new IncrementFunctionOp()).join(in2).map(new BinaryAsUnaryFunctionOp<>(new SumPairsFunctionOp()));
//		}
//
//		@Override
//		public Integer compute2(final Integer input1, final Integer input2) {
//			return input1 + 1 + input2;
//		}
//	}
//
//	public static class IncrementFunctionOp extends AbstractUnaryFunctionOp<Integer, Integer> implements
//		UnaryMappable<Integer, Integer>
//	{
//
//		@Override
//		public Integer compute1(final Integer input) {
//			return input + 1;
//		}
//	}
//
//	public static class SumPairsFunctionOp extends AbstractBinaryFunctionOp<Integer, Integer, Integer> implements
//		BinaryMappable<Integer, Integer, Integer>
//	{
//
//		@Override
//		public Integer compute2(final Integer input1, final Integer input2) {
//			return input1 + input2;
//		}
//	}
//
//	public static class ToStrFunctionOp<I> extends AbstractUnaryFunctionOp<I, String> implements
//		UnaryMappable<I, String>
//	{
//
//		@Override
//		public String compute1(final I input) {
//			final String output = input.toString();
//			System.out.println(output);
//			return output;
//		}
//	}
//}
