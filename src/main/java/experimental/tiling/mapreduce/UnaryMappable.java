
package experimental.tiling.mapreduce;

import experimental.tiling.DistributedList;

public interface UnaryMappable<I, O> extends UnaryDistributable<I, O> {

	@Override
	default DistributedList<?, O> getDistributionPlan(final DistributedList<?, I> c) {
		return c.map(this);
	}
}
