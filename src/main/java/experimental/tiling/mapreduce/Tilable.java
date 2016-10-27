
package experimental.tiling.mapreduce;

import experimental.tiling.Tiling;

public interface Tilable<I, O> {

	<II> Tiling<II, O> getTilingPlan(Tiling<II, I> t);
}
