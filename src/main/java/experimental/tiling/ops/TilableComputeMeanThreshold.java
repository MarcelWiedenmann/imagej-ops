
package experimental.tiling.ops;

import net.imagej.ops.Ops;
import net.imagej.ops.special.hybrid.AbstractUnaryHybridCF;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.histogram.Histogram1d;
import net.imglib2.histogram.Real1dBinMapper;
import net.imglib2.type.numeric.RealType;
import net.imglib2.view.Views;

import org.scijava.Priority;
import org.scijava.plugin.Plugin;

@Deprecated
@Plugin(type = Ops.Threshold.Mean.class, priority = Priority.LAST_PRIORITY)
public class TilableComputeMeanThreshold<T extends RealType<T>> extends
	AbstractUnaryHybridCF<RandomAccessibleInterval<T>, T> implements Ops.Threshold.Mean
{

	@Override
	public void compute1(final RandomAccessibleInterval<T> input, final T output) {
		final Histogram1d<T> hist = createHistogram(input);
		final long binPos = computeBin(hist);

		hist.getCenterValue(binPos, output);
	}

	@Override
	public T createOutput(final RandomAccessibleInterval<T> input) {
		return input.randomAccess().get().createVariable();
	}

	// -- Helper Methods --

	private Histogram1d<T> createHistogram(final RandomAccessibleInterval<T> input) {
		final T elem = input.randomAccess().get();

		final Real1dBinMapper<T> mapper = new Real1dBinMapper<>(elem.getMinValue(), elem.getMaxValue(), (long) (elem
			.getMaxValue() - elem.getMinValue() + 1), false);

		return new Histogram1d<>(Views.iterable(input), mapper);
	}

	private long computeBin(final Histogram1d<T> hist) {
		final long[] histogram = hist.toLongArray();
		int threshold = -1;
		double tot = 0, sum = 0;
		for (int i = 0; i < histogram.length; i++) {
			tot += histogram[i];
			sum += (i * histogram[i]);
		}
		threshold = (int) Math.floor(sum / tot);
		return threshold;
	}
}
