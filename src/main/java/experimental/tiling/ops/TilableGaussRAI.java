
package experimental.tiling.ops;

import net.imagej.ops.Ops;
import net.imagej.ops.special.hybrid.AbstractUnaryHybridCF;
import net.imglib2.RandomAccessible;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.algorithm.gauss3.Gauss3;
import net.imglib2.algorithm.gauss3.SeparableSymmetricConvolution;
import net.imglib2.exception.IncompatibleTypeException;
import net.imglib2.outofbounds.OutOfBoundsFactory;
import net.imglib2.outofbounds.OutOfBoundsMirrorFactory;
import net.imglib2.outofbounds.OutOfBoundsMirrorFactory.Boundary;
import net.imglib2.type.NativeType;
import net.imglib2.type.numeric.RealType;
import net.imglib2.type.numeric.real.FloatType;
import net.imglib2.view.Views;

import org.scijava.Priority;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;
import org.scijava.thread.ThreadService;

import experimental.tiling.mapreduce.TilableMapOverlap;

/**
 * Code mainly stolen from net.imagej.ops.filter.gauss.DefaultGaussRAI by Christian Dietz, University of Konstanz. For
 * the sake of easy demonstration of tiled operations w/o any own effort :D
 */
@Plugin(type = Ops.Filter.Gauss.class, priority = Priority.LAST_PRIORITY)
public class TilableGaussRAI<T extends RealType<T> & NativeType<T>> extends
	AbstractUnaryHybridCF<RandomAccessibleInterval<T>, RandomAccessibleInterval<T>> implements Ops.Filter.Gauss,
	TilableMapOverlap<RandomAccessibleInterval<T>, RandomAccessibleInterval<T>>
{

	@Parameter
	private ThreadService threads;

	@Parameter
	private double[] sigmas;

	@Parameter(required = false)
	private OutOfBoundsFactory<T, RandomAccessibleInterval<T>> outOfBounds;

	@Override
	public void compute1(final RandomAccessibleInterval<T> input, final RandomAccessibleInterval<T> output) {

		if (outOfBounds == null) outOfBounds = new OutOfBoundsMirrorFactory<>(Boundary.SINGLE);

		final RandomAccessible<FloatType> eIn = (RandomAccessible) Views.extend(input, outOfBounds);

		try {
			SeparableSymmetricConvolution.convolve(Gauss3.halfkernels(sigmas), eIn, output, threads.getExecutorService());
		}
		catch (final IncompatibleTypeException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public RandomAccessibleInterval<T> createOutput(final RandomAccessibleInterval<T> input) {
		return ops().create().img(input);
	}

	@Override
	public int[] getOverlap() {
		return Gauss3.halfkernelsizes(sigmas);
	}
}
