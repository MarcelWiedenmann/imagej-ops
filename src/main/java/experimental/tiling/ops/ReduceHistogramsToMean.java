
package experimental.tiling.ops;

import java.util.Iterator;

import net.imagej.ops.special.function.AbstractUnaryFunctionOp;
import net.imglib2.histogram.DiscreteFrequencyDistribution;
import net.imglib2.histogram.Histogram1d;

import org.scijava.plugin.Plugin;

@Plugin(type = ReduceHistogramsToMean.class)
public class ReduceHistogramsToMean<T> extends AbstractUnaryFunctionOp<Iterable<Histogram1d<T>>, T> {

	@Override
	public T compute1(final Iterable<Histogram1d<T>> input) {
		final Histogram1d<T> mergedHisto = new Histogram1d<>(input.iterator().next());

		final DiscreteFrequencyDistribution dfd = mergedHisto.dfd();
		final long[] binPos = new long[1];
		final Iterator<Histogram1d<T>> i = input.iterator();
		if (i.hasNext()) {
			final Histogram1d<T> histo = i.next();
			for (long b = 0; b < mergedHisto.getBinCount(); b++) {
				binPos[0] = b;
				final long freq = dfd.frequency(binPos);
				dfd.setFrequency(binPos, freq + histo.frequency(b));
			}
		}

		final T mean = input.iterator().next().firstDataValue();
		final long[] histogram = mergedHisto.toLongArray();
		int meanPos = -1;
		double tot = 0, sum = 0;
		for (int j = 0; j < histogram.length; j++) {
			tot += histogram[j];
			sum += (j * histogram[j]);
		}
		meanPos = (int) Math.floor(sum / tot);
		mergedHisto.getCenterValue(meanPos, mean);
		return mean;
	}
}
