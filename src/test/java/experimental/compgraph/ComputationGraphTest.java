
package experimental.compgraph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Iterator;

import net.imagej.ops.AbstractOpTest;
import net.imagej.ops.special.function.AbstractUnaryFunctionOp;

import org.junit.Test;

import experimental.compgraph.implementations.DefaultComputationBranch;
import experimental.compgraph.implementations.DefaultUnaryComputationGraphInputNode;
import experimental.compgraph.implementations.DefaultUnaryComputationGraphStageNode;
import experimental.compgraph.interfaces.ComputationBranch;
import experimental.compgraph.interfaces.ComputationGraphNode;

public class ComputationGraphTest extends AbstractOpTest {

	@Test
	public void test() {

		final StringToIntFunctionOp toInt = new StringToIntFunctionOp();
		final IncrementFunctionOp inc = new IncrementFunctionOp();
		final IntToStringFunctionOp toStr = new IntToStringFunctionOp();

		final int input = 1;

		// -- Nodes --

		final DefaultUnaryComputationGraphInputNode<String, Integer> n1 = new DefaultUnaryComputationGraphInputNode<>(
			toInt);
		final DefaultUnaryComputationGraphStageNode<Integer, Integer> n2 = new DefaultUnaryComputationGraphStageNode<>(n1,
			inc);
		final DefaultUnaryComputationGraphStageNode<Integer, Integer> n3 = new DefaultUnaryComputationGraphStageNode<>(n2,
			inc);
		final DefaultUnaryComputationGraphStageNode<Integer, Integer> n4 = new DefaultUnaryComputationGraphStageNode<>(n3,
			inc);
		final DefaultUnaryComputationGraphStageNode<Integer, String> n5 = new DefaultUnaryComputationGraphStageNode<>(n4,
			toStr);
		assertEquals(n2.getParent(), n1);
		assertEquals(n5.getParent(), n4);
		n1.setInput(String.valueOf(input));
		assertEquals(Integer.parseInt(n5.out()), input + 3);
		assertEquals(n3.out().intValue(), input + 2);
		n1.setInput(String.valueOf(input + 1));
		assertEquals(n3.out().intValue(), input + 3);
		n3.setParent(n1);
		assertEquals(n3.out().intValue(), input + 2);
		assertEquals(n1.compute1(String.valueOf(input)).intValue(), input);
		n1.setInput(String.valueOf(input));
		assertEquals(n1.in(), String.valueOf(input));
		assertEquals(n1.out().intValue(), input);
		n3.setParent(n2);

		// -- Branches --

		final ComputationBranch<String, String> branch = new DefaultComputationBranch<>(toInt).prepend(toStr).prepend(inc)
			.prepend(inc).prepend(toInt).append(toStr).prepend(toStr).prepend(inc).prepend(toInt);
		assertEquals(branch.getLength(), 9);
		assertEquals(branch.getStartNode().getFunc(), toInt);
		assertEquals(branch.getEndNode().getFunc(), toStr);
		assertEquals(branch.compute1(String.valueOf(input)), String.valueOf(input + 3));
		branch.setInput("");
		branch.setInput(String.valueOf(input));
		assertEquals(branch.out(), branch.compute1(String.valueOf(input)));
		assertEquals(branch.in(), String.valueOf(input));

		final ComputationBranch<String, String> branchCopy = branch.copy();
		assertFalse(branch.equals(branchCopy));
		assertEquals(branch.getLength(), branchCopy.getLength());
		assertFalse(branch.getEndNode().equals(branchCopy.getEndNode()));
		assertFalse(branch.getStartNode().equals(branchCopy.getStartNode()));
		assertEquals(branch.compute1(String.valueOf(input)), branchCopy.compute1(String.valueOf(input)));

		int i = 0;
		final Iterator<ComputationGraphNode<?>> branchIterator = branch.iterator();
		for (final ComputationGraphNode<?> nodeCopy : branchCopy) {
			final ComputationGraphNode<?> node = branchIterator.next();
			assertFalse(node.equals(nodeCopy));
			i++;
		}
		assertFalse(branchIterator.hasNext());
		assertEquals(i, branch.getLength());

		final ComputationBranch<Integer, Integer> branch2 = branch.append(toInt).prepend(toStr).prepend(inc).append(inc);
		assertFalse(branch.equals(branch2));
		assertEquals(branch2.getLength(), branch.getLength() + 4);
		assertEquals(branch2.compute1(input).intValue(), Integer.parseInt(branch.compute1(String.valueOf(input))) + 2);
		assertEquals(branch.in(), branch2.in().toString());
	}

	public static class StringToIntFunctionOp extends AbstractUnaryFunctionOp<String, Integer> {

		@Override
		public Integer compute1(final String input) {
			return new Integer(input);
		}
	}

	public static class IntToStringFunctionOp extends AbstractUnaryFunctionOp<Integer, String> {

		@Override
		public String compute1(final Integer input) {
			return input.toString();
		}
	}

	public static class IncrementFunctionOp extends AbstractUnaryFunctionOp<Integer, Integer> {

		@Override
		public Integer compute1(final Integer input) {
			return input + 1;
		}
	}
}
