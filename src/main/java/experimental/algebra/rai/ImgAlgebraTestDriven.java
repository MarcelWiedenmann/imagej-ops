package experimental.algebra.rai;

import java.util.function.BiFunction;
import java.util.function.Function;

import net.imagej.ops.special.function.AbstractUnaryFunctionOp;
import net.imagej.ops.special.hybrid.AbstractUnaryHybridCF;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.type.NativeType;
import net.imglib2.type.numeric.RealType;
import net.imglib2.type.numeric.real.DoubleType;

import experimental.algebra.DOpsCollection;
import experimental.algebra.OpsCollection;
import experimental.algebra.OpsGrid;

public class ImgAlgebraTestDriven {

	public <T extends RealType<T> & NativeType<T>> void funWithImages() {
		/* Some input elements */
		OpsRAI<T> inRAI = null;
		OpsCollection<OpsRAI<T>> inRAIs = null;

		/* -1: Scatter OpsRAI */
		DOpsTiling<T> tiling = inRAI.tiling(() -> new long[5]);
		OpsRAI<T> merge = tiling.mergeTiles();
		assert (merge.equals(tiling));

		/* 0: Partition OpsRAI in Collection */
		DOpsCollection<OpsRAI<T>> scatter = inRAIs.scatter((f) -> 16);

		// (a) distribution over images which distribute over tiles
		DOpsCollection<DOpsTiling<T>> map = scatter.map((o) -> o.map((r) -> r.tiling(() -> new long[5])));

		// (b) beautiful
		OpsCollection<DOpsTiling<T>> partitioned = inRAIs.partition(new MyPartitioner<T>());

		/* 1: TODO partition, do something and merge back */

		/* 2: TODO parallelize and do not merge back (groupby) */

		/* 3: TODO tiling, gauss and back */

		/* 4: TODO pairs */

		/* 5: TODO neighbors */

		/* 6: TODO filter */

		/* 7: TODO pixels: has to be evaluate lazy locally */

		/* 8: TODO RAI inputs */
		OpsCollection<OpsGrid<T>> c = null;
		DOpsCollection<OpsGrid<T>> scattered = c.scatter((f) -> 14);

		/* 9: TODO Mean per x,y plane in video */
		// DOpsTiling<T> oneDGrid = inRAI.tiling(() -> new long[] { 1024, 1024,
		// 1 });
		// DOpsRAI<DoubleType> distributedMeans = oneDGrid.map((c1) ->
		// c1.reduce(new DoubleType(), new MyMean<T>()));
		// OpsRAI<DoubleType> localMeans = distributedMeans.merge();

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

	public static class MyMoreComplexFunction<I, O>
			extends AbstractUnaryFunctionOp<RandomAccessibleInterval<I>, RandomAccessibleInterval<O>> {

		@Override
		public RandomAccessibleInterval<O> compute1(RandomAccessibleInterval<I> input) {
			// TODO Auto-generated method stub
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

	public static class MyPartitioner<T> implements Function<OpsRAI<T>, DOpsTiling<T>> {

		@Override
		public DOpsTiling<T> apply(OpsRAI<T> t) {
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
