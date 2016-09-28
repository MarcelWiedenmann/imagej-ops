
package experimental.tiling;

import net.imagej.ops.cached.CachedOpEnvironment.CachedFunctionOp;
import net.imglib2.RandomAccessibleInterval;

import org.scijava.service.SciJavaService;

public interface TilingService extends SciJavaService {

	public TilingOpEnvironment ops();

	public <I> TilingSchema<I> createSchema(RandomAccessibleInterval<I> in, TilingConfiguration config);

	public <I, O> Tiling<I, O> createTiling(final TilingSchema<I> schema, final CachedFunctionOp<I, O> function);

	public <I, IO, O> Tiling<I, O> concat(Tiling<I, IO> tiling, CachedFunctionOp<IO, O> function);

	public <I, O> O run(final Tiling<I, O> tiling);
}
