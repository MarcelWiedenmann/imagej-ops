
package experimental.tiling.ops;

import net.imagej.ops.Ops;
import net.imagej.ops.special.computer.BinaryComputerOp;
import net.imagej.ops.special.computer.Computers;
import net.imagej.ops.special.computer.UnaryComputerOp;
import net.imagej.ops.special.hybrid.AbstractBinaryHybridCF;
import net.imagej.ops.threshold.apply.ApplyThresholdComparable;
import net.imglib2.FinalDimensions;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.type.logic.BitType;
import net.imglib2.type.numeric.RealType;

import org.scijava.Priority;
import org.scijava.plugin.Plugin;

import experimental.tiling.ops.interfaces.TilableOp;

@Plugin(type = Ops.Threshold.Apply.class, priority = Priority.LAST_PRIORITY)
public class TilableApplyConstantThreshold<T extends RealType<T>> extends
	AbstractBinaryHybridCF<RandomAccessibleInterval<T>, T, RandomAccessibleInterval<BitType>> implements
	Ops.Threshold.Apply, TilableOp
{

	private BinaryComputerOp<T, T, BitType> applyThreshold;
	private UnaryComputerOp<RandomAccessibleInterval<T>, RandomAccessibleInterval<BitType>> mapper;

	@SuppressWarnings("unchecked")
	@Override
	public void compute2(final RandomAccessibleInterval<T> input1, final T input2,
		final RandomAccessibleInterval<BitType> output)
	{
		applyThreshold = Computers.binary(ops(), ApplyThresholdComparable.class, BitType.class, in2(), in2());

		mapper = (UnaryComputerOp) Computers.unary(ops(), Ops.Map.class, out() == null
			? RandomAccessibleInterval.class
			: out(), in1(), applyThreshold);

		applyThreshold.setInput2(input2);
		mapper.compute1(input1, output);
	}

	@Override
	public RandomAccessibleInterval<BitType> createOutput(final RandomAccessibleInterval<T> input1, final T input2) {
		final long[] inputDims = new long[input1.numDimensions()];
		input1.dimensions(inputDims);

		return ops().create().img(FinalDimensions.wrap(inputDims), new BitType());
	}
}
