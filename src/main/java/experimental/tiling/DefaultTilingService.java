
package experimental.tiling;

import net.imagej.ops.OpService;
import net.imagej.ops.cached.CachedOpEnvironment.CachedFunctionOp;
import net.imglib2.RandomAccessibleInterval;

import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;
import org.scijava.service.AbstractService;
import org.scijava.service.Service;

@Plugin(type = Service.class)
public class DefaultTilingService extends AbstractService implements TilingService {

	@Parameter
	private OpService os;

	private TilingOpEnvironment ops;

	@Override
	public TilingOpEnvironment ops() {
		return ops;
	}

	// -- Service --

	@Override
	public void initialize() {
		ops = new TilingOpEnvironment(os);
	}

	// -- TilingService --

	@Override
	public <I> TilingSchema<I> createSchema(final RandomAccessibleInterval<I> in, final TilingConfiguration config) {
		final TilingSchema<I> schema = config.generateSchema(in);
		return schema;
	}

	@Override
	public <I, O> Tiling<I, O> createTiling(final TilingSchema<I> schema, final CachedFunctionOp<I, O> function) {
		return new Tiling<I, O>(schema, function);
	}

	@Override
	public <I, IO, O> Tiling<I, O> concat(final Tiling<I, IO> tiling, final CachedFunctionOp<IO, O> function) {
		return new Tiling<I, O>(tiling, function);
	}

	@Override
	public <I, O> O run(final Tiling<I, O> tiling) {
		return tiling.run();
	}
}
