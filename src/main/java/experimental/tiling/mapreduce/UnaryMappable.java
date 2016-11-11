
package experimental.tiling.mapreduce;

import experimental.tiling.DistributedCollection;

public interface UnaryMappable<I, O> extends UnaryDistributable<I, O> {

	@Override
	default DistributedCollection<?, O> getDistributionPlan(final DistributedCollection<?, I> c) {
		return c.map(this);
	}
}
