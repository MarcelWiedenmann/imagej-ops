
package experimental.tiling;

import org.scijava.service.SciJavaService;

public interface TilingService extends SciJavaService {

	// TODO: Support for binary functions (fork-join)
	// TODO: Make use of method chaining (see ComputationBranch.append(..)/prepend(..)).

	// TODO

//	public TilingOpEnvironment ops();
//
//	public <I, O> TilingSchema<RandomAccessibleInterval<I>> create(RandomAccessibleInterval<I> in,
//		TilingConfiguration config);
//
//	public <I, O> REFACTOR_Old_Tiling<I, O> create(final TilingSchema<RandomAccessibleInterval<I>> schema,
//		final UnaryFunctionOp<RandomAccessibleInterval<I>, O> function);
//
//	public <I, IO, O> REFACTOR_Old_Tiling<I, O> concat(REFACTOR_Old_Tiling<I, IO> tiling, UnaryFunctionOp<IO, O> function);
//
//	public <I, O> O run(final REFACTOR_Old_Tiling<I, O> tiling);
}
