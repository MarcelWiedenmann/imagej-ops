
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
	public <I, O> TilingSchema<RandomAccessibleInterval<I>> create(final RandomAccessibleInterval<I> in,
		final TilingConfiguration config)
	{
		return config.generateSchema(in);
	}

	@Override
	public <I, O> Tiling<I, O> create(final TilingSchema<RandomAccessibleInterval<I>> schema,
		final UnaryFunctionOp<RandomAccessibleInterval<I>, O> function)
	{
//		return new Tiling<I, O>(schema, new LazyExecutionBranch<>(new CachedFunctionOp<>(function)));
		return new Tiling<I, O>(schema, new LazyExecutionBranch<>(function));
	}

	@Override
	public <I, IO, O> Tiling<I, O> concat(final Tiling<I, IO> tiling, final UnaryFunctionOp<IO, O> function) {
//		final LazyExecutionBranch<RandomAccessibleInterval<I>, O> branch = tiling.getBranch().appendLeaf(
//			new CachedFunctionOp<>(function));
		final LazyExecutionBranch<RandomAccessibleInterval<I>, O> branch = tiling.getBranch().appendLeaf(function);
		return new Tiling<>(tiling.getSchema(), branch);
	}

	@Override
	public <I, O> O run(final Tiling<I, O> tiling) {
		return tiling.get();
	}
}
