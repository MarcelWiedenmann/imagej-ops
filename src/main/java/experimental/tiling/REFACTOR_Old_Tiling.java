
package experimental.tiling;

// TODO

//public class REFACTOR_Old_Tiling<T, O> extends LazyExecution<RandomAccessibleInterval<T>, O> {
//
//	protected final TilingSchema<RandomAccessibleInterval<T>> schema;
//
//	public REFACTOR_Old_Tiling(final TilingSchema<RandomAccessibleInterval<T>> schema,
//		final LazyExecutionBranch<RandomAccessibleInterval<T>, O> branch)
//	{
//		super(schema.getInput(), branch/*.appendRoot(tilingOp);[SEE BELOW]*/);
//		// TODO/FIXME: We need to configure the schema's strategy according to the ops within the branch.
//		this.schema = schema;
//
//		/* TODO: Tiling as op
//		 * Implement tiling op [RAI] -> [TilingView] and put in branch
//		 * Same for [List<RAI>] -> [CombinedView] as reduce-op
//		 * I.e. tiling itself becomes part of the execution branch
//		 */
//	}
//
//	private REFACTOR_Old_Tiling(final REFACTOR_Old_Tiling<T, O> tiling) {
//		// FIXME: Passing branch to super only works if appendRoot replaces the LazyExecutionSource (or maybe we need a
//		// subbranch (<->substring method)
//		super(tiling.schema.getInput(), tiling.branch);
//		this.schema = tiling.schema;
//	}
//
//	public TilingSchema<RandomAccessibleInterval<T>> getSchema() {
//		return schema;
//	}
//
//	public LazyExecutionBranch<RandomAccessibleInterval<T>, O> getBranch() {
//		return branch;
//	}
//
//	public Iterator<Op> opIterator() {
//		// TODO: This is just a mockup to simulate a real iterator over the underlying execution graph.
//		// (Notice that a real iterator has to handle forks as well as sub-graphs etc).
//		final ArrayList<Op> ops = new ArrayList<>();
//		LazyExecutionNode<?, ?> l = branch.getLeaf();
//		while (l != null) {
//			if (l instanceof LazyExecutionStage) {
//				ops.add(((LazyExecutionStage) l).getOp());
//			}
//			l = l.getParent();
//		}
//		return ops.iterator();
//	}
//
//	public LazyCursor<RandomAccessibleInterval<T>, O> cursor() {
////		final TiledView<T> view = new TiledView<>(schema.getInput(), schema.getTilesPerDim());
////		return new LazyCursor<RandomAccessibleInterval<T>, O>(view.cursor(), branch);
//		// FIXME
//		return null;
//	}
//
//	@Override
//	public O get() {
//		final LazyCursor<RandomAccessibleInterval<T>, O> c = cursor();
//		final List<RandomAccessibleInterval> outs = new ArrayList<RandomAccessibleInterval>();
//		while (c.hasNext()) {
//			outs.add((RandomAccessibleInterval) c.next().get());
//		}
////		return (O) new CombinedView(outs, GridIndexMapper.createFromTiles(outs, Util.dimensionsToArray(schema
////			.getTilesPerDim())));
//		return null; // FIXME
//	}
//
//	@Override
//	public REFACTOR_Old_Tiling<T, O> copy() {
//		return new REFACTOR_Old_Tiling<T, O>(this);
//	}
//}
