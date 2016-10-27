
package experimental.tiling;

import java.util.Arrays;
import java.util.Iterator;

import net.imagej.ops.AbstractOpTest;
import net.imagej.ops.special.function.AbstractUnaryFunctionOp;
import net.imagej.ops.special.function.UnaryFunctionOp;

import org.junit.Test;

import experimental.compgraph.implementations.DefaultComputationBranch;
import experimental.tiling.mapreduce.Tilable;
import experimental.tiling.mapreduce.TilableMap;

public class TilingPlanTest extends AbstractOpTest {

	@Test
	public void test() {

		final Integer[] tiledInput = { 0, 1, 2, 3, 4, 5 };

		final MyVeryComplexProcessing p = new MyVeryComplexProcessing();
		final Tiling<Integer, Integer> t = p.getTilingPlan(new DefaultTiling<>());
		((DefaultTiling<Integer, Integer>) t).compute1(Arrays.asList(tiledInput));
	}

	public static class DefaultTiling<I, O> implements Tiling<I, O> {

		DefaultComputationBranch<I, O> branch;

		public DefaultTiling() {}

		private DefaultTiling(final DefaultComputationBranch<I, O> branch) {
			this.branch = branch;
		}

		@Override
		public <OO> Tiling<I, OO> map(final UnaryFunctionOp<O, OO> map) {
			return new DefaultTiling<I, OO>(branch.append(map));
		}

		@Override
		public <OO> Tiling<I, OO> forwardAggregate(final UnaryFunctionOp<Iterator<O>, OO> aggregate) {
			return new DefaultTiling<I, OO>(branch.append(new ComputationBranchAggregateOp<>()).append(aggregate));
		}

		@Override
		public <OO> Tiling<I, OO> treeAggregate(final UnaryFunctionOp<Iterator<O>, OO> aggregate) {
			return new DefaultTiling<I, OO>(branch.append(new ComputationBranchAggregateOp<>()).append(aggregate));
		}

		public O compute1(final Iterable<I> input) {
			branch.compute1(input);
		}

		public static class ComputationBranchAggregateOp<I> extends AbstractUnaryFunctionOp<I, Iterator<I>> {

			@Override
			public Iterator<I> compute1(final I input) {
				// TODO Auto-generated method stub
				return null;
			}
		}
	}

	public static class MyVeryComplexProcessing implements Tilable<Integer, Integer> {

		@Override
		public <II> Tiling<II, Integer> getTilingPlan(final Tiling<II, Integer> t) {
			return t.map(new IncrementFunctionOp()).forwardAggregate(new SumFunctionOp());
		}
	}

	public static class IncrementFunctionOp extends AbstractUnaryFunctionOp<Integer, Integer> implements
		TilableMap<Integer, Integer>
	{

		@Override
		public Integer compute1(final Integer input) {
			return input + 1;
		}
	}

	public static class SumFunctionOp extends AbstractUnaryFunctionOp<Iterator<Integer>, Integer> {

		@Override
		public Integer compute1(final Iterator<Integer> input) {
			int sum = 0;
			while (input.hasNext()) {
				sum += input.next();
			}
			return sum;
		}
	}
}
