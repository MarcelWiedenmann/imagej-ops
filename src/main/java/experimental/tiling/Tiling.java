
package experimental.tiling;

import java.util.ArrayList;
import java.util.Iterator;

import net.imagej.ops.Op;
import net.imglib2.Cursor;
import net.imglib2.RandomAccessibleInterval;

import experimental.tiling.execution.LazyExecutionBranch;
import experimental.tiling.view.CombinedView;
import experimental.tiling.view.TilingView;

public class Tiling<I extends RandomAccessibleInterval<?>, O> {

	// TODO/NB: This kind of tiling only works for simple tile-to-tile transformations which yield a RAI output at the
	// end of the tiling process.
	private final TilingSchema<I> schema;
	private final LazyExecutionBranch<RandomAccessibleInterval<I>, RandomAccessibleInterval<O>> branch;

	public Tiling(final TilingSchema<I> schema,
		final LazyExecutionBranch<RandomAccessibleInterval<I>, RandomAccessibleInterval<O>> branch)
	{
		// TODO/FIXME: We need to configure the schema's strategy according to the ops within the branch.
		this.schema = schema;
		this.branch = branch;
	}

	public TilingSchema<I> getSchema() {
		return schema;
	}

	public LazyExecutionBranch<RandomAccessibleInterval<I>, RandomAccessibleInterval<O>> getBranch() {
		return branch;
	}

	public Iterator<Op> opIterator() {
		// FIXME
		throw new RuntimeException("Not yet implemented");
	}

	// TODO: Change meaning of "I" everywhere!

	public TilingCursor<I, O> cursor() {
		final TilingView<IT> view = new TilingView<IT>(schema.getInput(), schema);
		final Cursor<RandomAccessibleInterval<I>> c = view.cursor();
		return new TilingCursor<I, O>(c, branch);
	}

	public CombinedView<O> run() {
		final TilingCursor<I, O> c = cursor();
		final ArrayList<RandomAccessibleInterval<O>> outs = new ArrayList<>();
		while (c.hasNext()) {
			// TODO: Override next()-method within TilingCursor to return RAI type-safely.
			outs.add((CachedRandomAccessibleInterval<I, O>) c.next());
		}
		return new CombinedView<O>(outs, null);
	}
}
