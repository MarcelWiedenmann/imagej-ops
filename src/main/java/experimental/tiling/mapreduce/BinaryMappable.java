//
//package experimental.tiling.mapreduce;
//
//import experimental.compgraph.BinaryAsUnaryFunctionOp;
//import experimental.tiling.DistributedList;
//
//public interface BinaryMappable<I1, I2, O> extends BinaryDistributable<I1, I2, O> {
//
//	@Override
//	default DistributedList<O> getDistributionPlan(final DistributedList<I1> in1, final DistributedList<I2> in2) {
//		return in1.join(in2).map(new BinaryAsUnaryFunctionOp<>(this));
//	}
//}
