
package experimental.tiling.mapreduce;

public interface BinaryTilableMap<I1, I2, O> extends BinaryTilable<I1, I2, O> {

	@Override
	default UnaryTilingNode<O> getTilingPlan(final BinaryTilingNode<I1, I2> t) {
		return t.map(this);
	}
}
