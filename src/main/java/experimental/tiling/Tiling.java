
package experimental.tiling;

import java.util.Iterator;

import net.imagej.ops.Op;
import net.imglib2.Cursor;
import net.imglib2.RandomAccessibleInterval;

import experimental.tiling.execution.LazyExecutionBranch;
import experimental.tiling.view.TilingView;

public class Tiling<I, O> {

	private final TilingSchema<I> schema;
	private final LazyExecutionBranch<I, O> branch;

	public Tiling(final TilingSchema<I> schema, final LazyExecutionBranch<I, O> branch) {
		// TODO/FIXME: We need to configure the schema's strategy according to the ops within the branch.
		this.schema = schema;
		this.branch = branch;
	}

	public TilingSchema<I> getSchema() {
		return schema;
	}

	public LazyExecutionBranch<I, O> getBranch() {
		return branch;
	}

	public Iterator<Op> opIterator() {
		// FIXME
		throw new RuntimeException("Not yet implemented");
	}

	public Cursor<CachedRandomAccessibleInterval<I, O>> cursor() {
		// NB: This kind of cursor conversion only works for simple tile-to-tile transformations (will be harder for
		// reductions etc.).
		// Also we assume that O is a CachedRandomAccessibleInterval which only holds for tile-to-tile transformations.
		final TilingView<I> view = new TilingView<>(schema.getInput(), schema);
		final Cursor<RandomAccessibleInterval<I>> c = view.cursor();
		return new TilingCursor<I, O>(c, branch);
	}

	public O run() {
//		final TilingView<T1> view = new TilingView<T1>(tiling.getSchema().getInput(), tiling.getSchema());
//		final Cursor<RandomAccessibleInterval<T1>> c = view.cursor();
//		final ArrayList<(RandomAccessibleInterval<T2>)> outputs = new ArrayList<>((int) tiling.getSchema().getNumTiles());
//		while (c.hasNext()) {
//			final I tile = (I) c.next();
//			tiling.getRoot().setParent(new LazyRootTile<I>(tile));
//			outputs.add(tiling.getLeaf().get());
//		}
//
//		final CombinedView<T2> combined = new CombinedView<T2>(outputs, null);
		throw new RuntimeException("Not yet implemented");
	}
}
