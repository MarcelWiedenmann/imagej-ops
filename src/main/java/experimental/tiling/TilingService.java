
package experimental.tiling;

import net.imagej.ops.special.function.UnaryFunctionOp;
import net.imglib2.RandomAccessibleInterval;

import org.scijava.service.SciJavaService;

public interface TilingService extends SciJavaService {

	// TODO: Support for binary functions.

	public TilingOpEnvironment ops();

	public <I, O> TilingSchema<RandomAccessibleInterval<I>> create(RandomAccessibleInterval<I> in,
		TilingConfiguration config);

	public <I, O> Tiling<I, O> create(final TilingSchema<RandomAccessibleInterval<I>> schema,
		final UnaryFunctionOp<RandomAccessibleInterval<I>, O> function);

	public <I, IO, O> Tiling<I, O> concat(Tiling<I, IO> tiling, UnaryFunctionOp<IO, O> function);

	public <I, O> O run(final Tiling<I, O> tiling);
}
