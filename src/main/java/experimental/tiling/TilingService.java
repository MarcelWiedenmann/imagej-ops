
package experimental.tiling;

import net.imagej.ops.cached.CachedOpEnvironment.CachedFunctionOp;
import net.imglib2.RandomAccessibleInterval;

import org.scijava.service.SciJavaService;

public interface TilingService extends SciJavaService {

	<I, O> Tiling<I, O> create(RandomAccessibleInterval<I> in, TilingConfiguration config);

	<I, IO, O> Tiling<I, O> concat(Tiling<I, IO> tiling, CachedFunctionOp<IO, O> function);

	<I, O> O run(Tiling<I, O> tiling);
}
