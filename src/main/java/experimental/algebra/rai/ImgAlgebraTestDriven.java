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
import experimental.algebra.OpsGrid;

public class ImgAlgebraTestDriven {

	// FIXME
	// is there a nicer way to directly transform OpsCollection<T> to Grid<T>
	// etc.?! (reshape?).

	//

	public <T extends RealType<T> & NativeType<T>> void funWithImages() {

		/* fun with input elements */
		OpsRAI<T> in = null;

		/* 0: Working with partitioned */
		OpsTiling<T> partitioned = in.tiling(() -> new long[] { 1024, 1024, 3 });

		/* 1: TODO partition, do something and merge back */

		// TODO can we make sure that this is also an OpsRAI<T> again?! Do we
		// need mapTile?
		OpsTiling<T> tiling = partitioned.mapTiles(new MyMoreComplexFunction<T, T>());
		OpsRAI<T> back = tiling.flatten();

		/* 2: TODO parallelize and do not merge back (groupby) */
		OpsCollection<Double> map2 = in.group((f) -> 0).map((f) -> 8.0d);

		/* 4: TODO pairs */

		/* 5: TODO neighbors */

		/* 6: TODO filter */

		/* 7: TODO pixels: has to be evaluate lazy locally */

		/* 8: Mean per x,y plane in video */
		// each XY plane
		OpsGrid<Double> map = in.tiling(() -> new long[] { 1, 1, 512 }).map((f) -> 5.0);

		map.stream();

		/* 9: Work on Distributed over time */
		OpsCollection<OpsRAI<T>> grid = null;

		// just to show a different way to partitioning. must be in the graph,
		// too!
		OpsCollection<OpsTiling<T>> tiled = grid.map((t) -> t.tiling(() -> new long[5]));

		// if someone can visualize this LAZY graphy he is genious.
		OpsBoundedStream<OpsTiling<T>> stream = tiled.stream();

		/**
		 * 10: partition over Z and do gauss in 2D X,Y and then partition over X
		 * to do gauss in Z,Y
		 */

		/*
		 * Assumption: The following code is the code of someone who implements
		 * an algorithm. Here two things are confusing: He may use the tiling
		 * functionality for logical reasons, without giving any hints how the
		 * algorithm would behave if an different tiling strategy was chosen.
		 * 
		 * Question: Do we have to explicitly state that this is just a logical
		 * tiling for what ever reason?
		 * 
		 * Idea: Maybe this example is a good reason to ask the user to provide
		 * an implementation of an Op given an already existing `OpsTiling`.
		 * Problem: How can the user then tell us that overlap is required?
		 * Maybe the provided tiling has config options?
		 * 
		 * Additional Idea: What about having both: Standard computation plan
		 * and if distributable a distributable distribution plan?
		 * 
		 * Marcel. Übernehmen Sie ;-)
		 */

		OpsRAI<DoubleType> first = in.tiling(() -> new long[] { 1, 1, 512 })
				.mapTiles(new MyMoreComplexFunction<T, DoubleType>()).flatten();

		OpsRAI<DoubleType> second = in.tiling(() -> new long[] { 512, 1, 1 })
				.mapTiles(new MyMoreComplexFunction<T, DoubleType>()).flatten();

		OpsRAI<DoubleType> third = in.tiling(() -> new long[] { 1, 1, 512 })
				.mapTiles(new MyMoreComplexFunction<T, DoubleType>()).flatten();

		/**
		 * 11: Do something per pixel logically, but find a nice tiling for it
		 */

		// we shouldn't create each branch explicitly. we should rather create
		// tiles and use converters (imglib2) if possible :-)
		OpsGrid<DoubleType> mapped = in.map(new MyHybridSimpleMapper<T, DoubleType>());
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

	public static class MyTiler<T> implements Function<RandomAccessibleInterval<T>, OpsTiling<T>> {

		@Override
		public OpsTiling<T> apply(RandomAccessibleInterval<T> t) {
			// TODO Auto-generated method stub
			return null;
		}

	}
}