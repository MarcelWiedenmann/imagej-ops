
package experimental.tiling.ops;

import net.imagej.ops.Ops;
import net.imagej.ops.special.hybrid.AbstractUnaryHybridCF;
import net.imglib2.Dimensions;
import net.imglib2.FinalDimensions;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.histogram.Histogram1d;
import net.imglib2.histogram.Real1dBinMapper;
import net.imglib2.type.numeric.RealType;
import net.imglib2.view.Views;

import org.scijava.Priority;
import org.scijava.plugin.Plugin;

@Plugin(type = Ops.Image.Histogram.class, priority = Priority.FIRST_PRIORITY)
public class TilableComputeHistogram<T extends RealType<T>> extends
	AbstractUnaryHybridCF<RandomAccessibleInterval<T>, Histogram1d<T>> implements Ops.Image.Histogram,
	TilableNeighborhoodOp
{

	@Override
	public void compute1(final RandomAccessibleInterval<T> input, final Histogram1d<T> output) {
		// Work is done in createOutput-method.
	}

	@Override
	public Histogram1d<T> createOutput(final RandomAccessibleInterval<T> input) {
		return createHistogram(input);
	}

	// -- TilableNeighborhoodOp --

	@Override
	public Dimensions getOverlap() {
		return new FinalDimensions(0);
	}

	// -- Helper Methods --

	private Histogram1d<T> createHistogram(final RandomAccessibleInterval<T> input) {
		T elem = input.randomAccess().get();

		final Real1dBinMapper<T> mapper = new Real1dBinMapper<>(elem.getMinValue(), elem.getMaxValue(), (long) (elem
			.getMaxValue() - elem.getMinValue() + 1), false);

		return new Histogram1d<>(Views.iterable(input), mapper);
	}
}
