
package experimental.tiling.mapreduce;

import net.imglib2.util.Pair;

import experimental.tiling.DistributedList;

public interface BinaryMappable<I1, I2, O> extends BinaryDistributable<I1, I2, O> {

	@Override
	default DistributedList<? extends Pair<?, ?>, O> getDistributionPlan(final DistributedList<?, I1> c1,
		final DistributedList<?, I2> c2)
	{
		return c1.join(c2).map(this);
	}
}
