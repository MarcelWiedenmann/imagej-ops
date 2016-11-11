
package experimental.tiling.mapreduce;

import net.imagej.ops.Ops.Join;

import experimental.tiling.DistributedCollection;

public interface BinaryMappable<I1, I2, O> extends BinaryDistributable<I1, I2, O> {

	@Override
	default Join<DistributedCollection<?, I1>, DistributedCollection<?, I2>, O> getDistributionPlan(
		final DistributedCollection<?, I1> c1, final DistributedCollection<?, I2> c2)
	{
		return c1.joinFirst(c2, this);
	}
}
