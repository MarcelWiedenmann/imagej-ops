
package experimental.tiling.ops;

import net.imagej.ops.Ops;
import net.imagej.ops.special.computer.AbstractUnaryComputerOp;
import net.imagej.ops.special.computer.Computers;
import net.imagej.ops.special.computer.UnaryComputerOp;
import net.imagej.ops.special.hybrid.AbstractUnaryHybridCF;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.type.numeric.RealType;

import org.scijava.Priority;
import org.scijava.plugin.Plugin;

import experimental.tiling.ops.interfaces.TilableOp;

/**
 * Code mainly stolen from net.imagej.ops.image.invert.InvertII by Martin Horn, University of Konstanz. For the sake of
 * easy demonstration of tiled operations w/o any own effort :D
 */
@Plugin(type = Ops.Image.Invert.class, priority = Priority.LAST_PRIORITY)
public class TilableInverter<T extends RealType<T>> extends
	AbstractUnaryHybridCF<RandomAccessibleInterval<T>, RandomAccessibleInterval<T>> implements Ops.Image.Invert,
	TilableOp
{

	private UnaryComputerOp<RandomAccessibleInterval<T>, RandomAccessibleInterval<T>> mapper;

	@Override
	public void initialize() {
		// TODO: Does not work yet, as initialize is called using a different in()
		// then the actual input in this.compute1. Therefore initialization is done
		// in compute1.

		// final T inType = in().randomAccess().get();
		// final double minVal = inType.getMinValue();
		// final UnaryComputerOp<T, T> invert = minVal < 0 ? new
		// SignedRealInvert<>() : new UnsignedRealInvert<>(inType
		// .getMaxValue());
		// mapper = Computers.unary(ops(), Ops.Map.class, createOutput(in()), in(),
		// invert);
	}

	@Override
	public void compute1(final RandomAccessibleInterval<T> input, final RandomAccessibleInterval<T> output) {
		final T inType = input.randomAccess().get();
		final double minVal = inType.getMinValue();
		final UnaryComputerOp<T, T> invert = minVal < 0
			? new SignedRealInvert<>()
			: new UnsignedRealInvert<>(inType.getMaxValue());
		mapper = Computers.unary(ops(), Ops.Map.class, output, input, invert);

		mapper.compute1(input, output);
	}

	@Override
	public RandomAccessibleInterval<T> createOutput(final RandomAccessibleInterval<T> input) {
		setOutput((RandomAccessibleInterval<T>) ops().create().img(input));
		return out();
	}

	// -- Inner Classes --

	private class SignedRealInvert<II extends RealType<II>, OO extends RealType<OO>> extends
		AbstractUnaryComputerOp<II, OO>
	{

		@Override
		public void compute1(final II x, final OO output) {
			final double value = x.getRealDouble() * -1.0 - 1;
			output.setReal(value);
		}

	}

	private class UnsignedRealInvert<II extends RealType<II>, OO extends RealType<OO>> extends
		AbstractUnaryComputerOp<II, OO>
	{

		private final double max;

		/**
		 * @param max - maximum value of the range to invert about
		 */
		public UnsignedRealInvert(final double max) {
			this.max = max;
		}

		@Override
		public void compute1(final II x, final OO output) {
			final double value = max - x.getRealDouble();
			output.setReal(value);
		}
	}
}
