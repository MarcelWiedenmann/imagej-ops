
package experimental.tiling.mapreduce;

import net.imagej.ops.special.function.UnaryFunctionOp;

import experimental.tiling.Tiling;

public interface TilableMap<I, O> extends Tilable<I, O>, UnaryFunctionOp<I, O> {

	@Override
	default <II> Tiling<II, O> getTilingPlan(final Tiling<II, I> t) {
		return t.map(this);
	}
}
