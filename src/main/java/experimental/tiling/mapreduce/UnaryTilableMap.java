
package experimental.tiling.mapreduce;

public interface UnaryTilableMap<I, O> extends UnaryTilable<I, O> {

	@Override
	default UnaryTilingNode<O> getTilingPlan(final UnaryTilingNode<I> t) {
		return t.map(this);
	}
}
