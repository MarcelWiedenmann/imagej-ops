
package experimental.tiling;

import java.util.Iterator;

import net.imagej.ops.Op;
import net.imglib2.RandomAccessibleInterval;

import experimental.tiling.execution.LazyCursor;
import experimental.tiling.execution.LazyExecution;
import experimental.tiling.execution.LazyExecutionBranch;
import experimental.tiling.view.TilingView;

public class Tiling<T, O> extends LazyExecution<RandomAccessibleInterval<T>, O> {

	protected final TilingSchema<RandomAccessibleInterval<T>> schema;

	public Tiling(final TilingSchema<RandomAccessibleInterval<T>> schema,
		final LazyExecutionBranch<RandomAccessibleInterval<T>, O> branch)
	{
		super(schema.getInput(), branch/*.appendRoot(tilingOp);[SEE BELOW]*/);
		// TODO/FIXME: We need to configure the schema's strategy according to the ops within the branch.
		this.schema = schema;

		/* TODO:
		 * Implement tiling op [RAI] -> [TilingView] and put in branch
		 * Same for [List<RAI>] -> [CombinedView] as reduce-op
		 * I.e. tiling itself becomes part of the execution branch
		 */
	}

	private Tiling(final Tiling<T, O> tiling) {
		// TODO: passing branch to super only works if appendRoot replaces the LazyExecutionSource (or maybe we need a
		// subbranch (<->substring method)
		super(tiling.schema.getInput(), tiling.branch);
		this.schema = tiling.schema;
	}

	public TilingSchema<RandomAccessibleInterval<T>> getSchema() {
		return schema;
	}

	public LazyExecutionBranch<RandomAccessibleInterval<T>, O> getBranch() {
		return branch;
	}

	public Iterator<Op> opIterator() {
		// FIXME
		throw new RuntimeException("Not yet implemented");
	}

	public LazyCursor<RandomAccessibleInterval<T>, O> cursor() {
		final TilingView<T> view = new TilingView<T>(schema.getInput(), schema);
		return new LazyCursor<RandomAccessibleInterval<T>, O>(view.cursor(), branch);
	}

	@Override
	public O get() {
//	final TilingCursor<I, O> c = cursor();
//	final ArrayList<RandomAccessibleInterval<O>> outs = new ArrayList<>();
//	while (c.hasNext()) {
//		// TODO: Override next()-method within TilingCursor to return RAI type-safely.
//		outs.add((CachedRandomAccessibleInterval<I, O>) c.next());
//	}
//	return new CombinedView<O>(outs, /*...*/);
		throw new RuntimeException("Not yet implemented");
	}

	@Override
	public Tiling<T, O> copy() {
		return new Tiling<T, O>(this);
	}
}
