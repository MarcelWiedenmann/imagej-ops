
package experimental.tiling;

// TODO

//import net.imagej.ops.OpService;
//import net.imagej.ops.special.function.UnaryFunctionOp;
//import net.imglib2.RandomAccessibleInterval;
//
//import org.scijava.plugin.Parameter;
//import org.scijava.plugin.Plugin;
//import org.scijava.service.AbstractService;
//import org.scijava.service.Service;
//
//@Plugin(type = Service.class)
//public class DefaultTilingService extends AbstractService implements TilingService {
//
//	@Parameter
//	private OpService os;
//
//	private TilingOpEnvironment ops;
//
//	@Override
//	public TilingOpEnvironment ops() {
//		return ops;
//	}
//
//	// -- Service --
//
//	@Override
//	public void initialize() {
//		ops = new TilingOpEnvironment(os);
//	}
//
//	// -- TilingService --
//
//	@Override
//	public <I, O> TilingSchema<RandomAccessibleInterval<I>> create(final RandomAccessibleInterval<I> in,
//		final TilingConfiguration config)
//	{
//		return config.generateSchema(in);
//	}
//
//	@Override
//	public <I, O> REFACTOR_Old_Tiling<I, O> create(final TilingSchema<RandomAccessibleInterval<I>> schema,
//		final UnaryFunctionOp<RandomAccessibleInterval<I>, O> function)
//	{
//// return new REFACTOR_Old_Tiling<I, O>(schema, new LazyExecutionBranch<>(function));
//		return null; // TODO
//	}
//
//	@Override
//	public <I, IO, O> REFACTOR_Old_Tiling<I, O> concat(final REFACTOR_Old_Tiling<I, IO> tiling,
//		final UnaryFunctionOp<IO, O> function)
//	{
////		final LazyExecutionBranch<RandomAccessibleInterval<I>, O> branch = tiling.getBranch().appendLeaf(function);
////		return new REFACTOR_Old_Tiling<>(tiling.getSchema(), branch);
//		return null; // TODO
//	}
//
//	@Override
//	public <I, O> O run(final REFACTOR_Old_Tiling<I, O> tiling) {
//		return tiling.get();
//	}
//}
