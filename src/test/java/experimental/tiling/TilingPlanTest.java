
package experimental.tiling;

import java.util.Arrays;
import java.util.Iterator;

import net.imagej.ops.AbstractOpTest;
import net.imagej.ops.special.function.AbstractUnaryFunctionOp;

import org.junit.Test;

import experimental.tiling.mapreduce.Tilable;
import experimental.tiling.mapreduce.TilableMap;

public class TilingPlanTest extends AbstractOpTest {

	@Test
	public void test() {

		final Integer[] tiledInput = { 0, 1, 2, 3, 4, 5 };

		// TODO: DefaultTiling implementation needed.

		final MyVeryComplexProcessing p = new MyVeryComplexProcessing();
		final Tiling<Integer, Integer> t = p.getTilingPlan(new DefaultTiling<>());
		((DefaultTiling<Integer, Integer>) t).compute1(Arrays.asList(tiledInput));
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
