
package experimental.tiling;

import net.imagej.ops.special.function.UnaryFunctionOp;
import net.imglib2.RandomAccessibleInterval;

import org.scijava.service.SciJavaService;

public interface TilingService extends SciJavaService {

	public TilingOpEnvironment ops();

	public <I extends RandomAccessibleInterval<?>, O> Tiling<I, O> create(I in, TilingConfiguration config);

	public <I, IO, O> Tiling<I, O> concat(Tiling<I, IO> tiling, UnaryFunctionOp<IO, O> function);

	public <I, O> O run(final Tiling<I, O> tiling);
}
