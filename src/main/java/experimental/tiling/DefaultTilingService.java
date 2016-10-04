
package experimental.tiling;

import net.imagej.ops.OpService;
import net.imagej.ops.special.function.UnaryFunctionOp;
import net.imglib2.RandomAccessibleInterval;

import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;
import org.scijava.service.AbstractService;
import org.scijava.service.Service;

import experimental.tiling.execution.LazyExecutionBranch;

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
	public <I extends RandomAccessibleInterval<?>, O> Tiling<I, O> create(final I in, final TilingConfiguration config) {
		final TilingSchema<I> schema = config.generateSchema(in);
		// TODO: Allow "Null-LazyExecutionBranch".
		return new Tiling<I, O>(schema, LazyExecutionBranch.Null);
	}

	@Override
	public <I, IO, O> Tiling<I, O> concat(final Tiling<I, IO> tiling, final UnaryFunctionOp<IO, O> function) {
		// TODO: Wrap function in CachedFunctionOp.
		return new Tiling<I, O>(tiling, function);
	}

	@Override
	public <I, O> O run(final Tiling<I, O> tiling) {
		return tiling.run();
	}
}
