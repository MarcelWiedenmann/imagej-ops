
package experimental.tiling;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.imagej.ops.Op;
import net.imglib2.RandomAccessibleInterval;

import experimental.tiling.execution.LazyCursor;
import experimental.tiling.execution.LazyExecution;
import experimental.tiling.execution.LazyExecutionBranch;
import experimental.tiling.execution.LazyExecutionNode;
import experimental.tiling.execution.LazyExecutionStage;
import experimental.tiling.misc.Util;
import experimental.tiling.view.CombinedView;
import experimental.tiling.view.TileIndexMapper;
import experimental.tiling.view.TiledView;

public class Tiling<T, O> extends LazyExecution<RandomAccessibleInterval<T>, O> {

	protected final TilingSchema<RandomAccessibleInterval<T>> schema;

	public Tiling(final TilingSchema<RandomAccessibleInterval<T>> schema,
		final LazyExecutionBranch<RandomAccessibleInterval<T>, O> branch)
	{
		super(schema.getInput(), branch/*.appendRoot(tilingOp);[SEE BELOW]*/);
		// TODO/FIXME: We need to configure the schema's strategy according to the ops within the branch.
		this.schema = schema;

		/* TODO: Tiling as op
		 * Implement tiling op [RAI] -> [TilingView] and put in branch
		 * Same for [List<RAI>] -> [CombinedView] as reduce-op
		 * I.e. tiling itself becomes part of the execution branch
		 */
	}

	private Tiling(final Tiling<T, O> tiling) {
		// FIXME: Passing branch to super only works if appendRoot replaces the LazyExecutionSource (or maybe we need a
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
		// TODO: This is just a mockup to simulate a real iterator over the underlying execution graph.
		// (Notice that a real iterator has to handle forks as well as sub-graphs etc).
		final ArrayList<Op> ops = new ArrayList<>();
		LazyExecutionNode<?, ?> l = branch.getLeaf();
		while (l != null) {
			if (l instanceof LazyExecutionStage) {
				ops.add(((LazyExecutionStage) l).getOp());
			}
			l = l.getParent();
		}
		return ops.iterator();
	}

	public LazyCursor<RandomAccessibleInterval<T>, O> cursor() {
		final TiledView<T> view = new TiledView<>(schema.getInput(), schema.getTilesPerDim());
		return new LazyCursor<RandomAccessibleInterval<T>, O>(view.cursor(), branch);
	}

	@Override
	public O get() {
		final LazyCursor<RandomAccessibleInterval<T>, O> c = cursor();
		final List<RandomAccessibleInterval> outs = new ArrayList<RandomAccessibleInterval>();
		while (c.hasNext()) {
			outs.add((RandomAccessibleInterval) c.next().get());
		}
		return (O) new CombinedView(outs, TileIndexMapper.createFromTiles(outs, Util.dimensionsToArray(schema
			.getTilesPerDim())));
	}

	@Override
	public Tiling<T, O> copy() {
		return new Tiling<T, O>(this);
	}
}
