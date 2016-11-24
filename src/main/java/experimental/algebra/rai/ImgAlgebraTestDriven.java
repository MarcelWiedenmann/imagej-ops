package experimental.algebra.rai;

import java.util.function.BiFunction;
import java.util.function.Function;

import net.imagej.ops.special.function.AbstractUnaryFunctionOp;
import net.imagej.ops.special.hybrid.AbstractUnaryHybridCF;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.type.NativeType;
import net.imglib2.type.numeric.RealType;
import net.imglib2.type.numeric.real.DoubleType;

import experimental.algebra.OpsBoundedStream;
import experimental.algebra.OpsCollection;
import experimental.algebra.OpsElement;
import experimental.algebra.OpsGrid;

public class ImgAlgebraTestDriven {

	public <T extends RealType<T> & NativeType<T>> void funWithImages() {

		/* fun with input elements */
		OpsElement<RandomAccessibleInterval<T>> elementInput = null;

		/* 0: Working with Distributed */
		OpsElement<OpsTiling<T>> partition = elementInput.partition(new MyPartitioner<T>());
		partition.map((t) -> t.scatter(null);

		// if partitioned, we have to take care about what we are actually doing
		// in the map
		OpsElement<OpsGrid<RandomAccessibleInterval<T>>> map2 = partition
				.map((t) -> t.map(new MyMoreComplexFunction<T, T>()));

		/* 1: TODO partition, do something and merge back */

		/* 2: TODO parallelize and do not merge back (groupby) */

		/* 3: TODO tiling, gauss and back */

		/* 4: TODO pairs */

		/* 5: TODO neighbors */

		/* 6: TODO filter */

		/* 7: TODO pixels: has to be evaluate lazy locally */

		/* 8: Mean per x,y plane in video */
		OpsElement<OpsGrid<Double>> map = elementInput.partition(new MyPartitioner<T>()).map((t) -> t.map((o) -> 5.0));
		// is there a nicer way to directly transform OpsCollection<T> to
		// Grid<T> etc.?! (reshape?).
		map.map((g) -> g.stream());

		/* 9: Work on Distributed over time */
		OpsCollection<OpsTiling<T>> manyPartitions = elementInput.partition(new MyPartitioner<T>());
		OpsBoundedStream<OpsTiling<T>> partitionStream = manyPartitions.stream();
		
		/* 10: partition over Z and do gauss in 2D X,Y and then partition over X to do gauss in  Z,Y */
		/* Here we will do a blockwise partitioning X,Y,Z e and logically apply partitionings request by ops */
		
		/* 11: Do something per pixel logically, but find a nice tiling for it */
	}

	// TODO
	public void ccaWithFilter() {

		// OpsRAI<T> bigImg = null;
		// final long[] tileSize = null;

		// OpsRAI<RandomAccessibleInterval<LabelingType<String>>> cca =
		// bigImg.partition(() -> tileSize)
		// .map(new DefaultGaussRAI<T>()).map((t) -> ArrayImgs.bits(1, 2,
		// 3)).map((t) -> new ImgLabeling<>(null));

		// How to get back an entire image?
		// OpsCollection<ArrayList<RandomAccessibleInterval<LabelingType<String>>>>
		// reduce = cca
		// .map((r) -> new ArrayList<>())
		// .reduce(new ArrayList<>(), (m, r) -> m.addAll(r));

		// OpsGrid<RandomAccessibleInterval<LabelingType<String>>> filtered =
		// rai.filter(new FinalInterval(5, 5));

		// OpsTiling<LabelingType<String>> ccaPartition = filtered.partition(()
		// -> tileSize);

		// option 1: use java to distribute via forEach on each partition
		// OpsGrid<LabelRegions<String>> regionsPerTile = ccaPartition.map((l)
		// -> new LabelRegions<>(l));

		// all label regions
		// OpsCollection<LabelRegions<String>> regions =
		// regionsPerTile.reduce(null, (m, r) -> r);

		// now we need a really strange join construct.
		// we want to match all label-regions on the corresponding tilings
		// WITHOUT copying (too much) data around. We want to copy the regions
		// around, not the image data!
		// if this doesn't work, we could think of copying IterableRegion<T>
		// around if necessary.

	}

	/**
	 * Helpers
	 */

	public static class MyHybridSimpleMapper<I, O> extends AbstractUnaryHybridCF<I, O> {

		@Override
		public void compute1(I input, O output) {
			// TODO Auto-generated method stub

		}

		@Override
		public O createOutput(I input) {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public static class MyNeighborhoodFunction<I, O>
			extends AbstractUnaryFunctionOp<RandomAccessibleInterval<I>, RandomAccessibleInterval<O>> {

		@Override
		public RandomAccessibleInterval<O> compute1(RandomAccessibleInterval<I> input) {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public static class MyComplexSlicer<I, O>
			extends AbstractUnaryFunctionOp<RandomAccessibleInterval<I>, RandomAccessibleInterval<O>> {

		@Override
		public RandomAccessibleInterval<O> compute1(RandomAccessibleInterval<I> input) {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public static class MyMoreComplexFunction<I, O>
			extends AbstractUnaryFunctionOp<RandomAccessibleInterval<I>, RandomAccessibleInterval<O>>
			implements OpsPlanable<RandomAccessibleInterval<I>, RandomAccessibleInterval<O>> {

		@Override
		public RandomAccessibleInterval<O> compute1(RandomAccessibleInterval<I> input) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public OpsCollection<RandomAccessibleInterval<O>> getPlan(OpsElement<RandomAccessibleInterval<I>> tiling) {
			OpsCollection<OpsTiling<I>> partition = tiling.partition(new MyPartitioner<I>());
			return null;
		}

	}

	public static class MyMean<T> implements BiFunction<DoubleType, RandomAccessibleInterval<T>, DoubleType> {

		@Override
		public DoubleType apply(DoubleType memo, RandomAccessibleInterval<T> u) {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public static class MyPartitioner<T> implements Function<RandomAccessibleInterval<T>, OpsTiling<T>> {

		@Override
		public OpsTiling<T> apply(RandomAccessibleInterval<T> t) {
			// TODO Auto-generated method stub
			return null;
		}

	}

	// public static class MyPartitionFunction<I, O>
	// extends AbstractUnaryFunctionOp<RandomAccessibleInterval<I>,
	// RandomAccessibleInterval<O>> {
	//
	// @Override
	// public RandomAccessibleInterval<O> compute1(RandomAccessibleInterval<I>
	// input) {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// }
}
