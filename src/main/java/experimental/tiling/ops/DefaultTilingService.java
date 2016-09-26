
package experimental.tiling.ops;

import net.imagej.ops.OpService;
import net.imagej.ops.cached.CachedOpEnvironment.CachedFunctionOp;
import net.imglib2.RandomAccessibleInterval;

import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;
import org.scijava.service.AbstractService;
import org.scijava.service.Service;

import experimental.tiling.Tiling;
import experimental.tiling.TilingConfiguration;

@Plugin(type = Service.class)
public class DefaultTilingService extends AbstractService implements TilingService {

	@Parameter
	private OpService os;

	private TilingOpEnvironment ops;

	public TilingOpEnvironment ops() {
		return ops;
	}

	// -- Service --

	@Override
	public void initialize() {
		ops = new TilingOpEnvironment(os);
	}

	// -- TilingService --

	@SuppressWarnings("unchecked")
	@Override
	public <I, O> Tiling<I, O> create(final RandomAccessibleInterval<I> in, final TilingConfiguration config) {

		final Tiling<I, O> tiling = config.generateTiling(in);
		return tiling;
	}

	@Override
	public <I, IO, O> Tiling<I, O> concat(final Tiling<I, IO> tiling, final CachedFunctionOp<IO, O> function) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <I, O> O run(final Tiling<I, O> tiling) {
		// TODO Auto-generated method stub
		return null;
	}
}
