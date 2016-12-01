
package experimental.compgraph.channel.collection.tiling;

import java.util.function.BiFunction;
import java.util.function.Function;

import net.imagej.ops.special.function.AbstractUnaryFunctionOp;
import net.imagej.ops.special.hybrid.AbstractUnaryHybridCF;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.histogram.Histogram1d;
import net.imglib2.img.array.ArrayImgs;
import net.imglib2.roi.labeling.LabelRegion;
import net.imglib2.roi.labeling.LabelingType;
import net.imglib2.type.NativeType;
import net.imglib2.type.logic.BitType;
import net.imglib2.type.numeric.RealType;
import net.imglib2.type.numeric.real.DoubleType;
import net.imglib2.util.Pair;

import experimental.compgraph.channel.OpsChannel;
import experimental.compgraph.channel.collection.OpsCollection;
import experimental.compgraph.channel.collection.OpsElement;
import experimental.compgraph.channel.collection.OpsGrid;
import experimental.compgraph.channel.collection.OpsList;
import experimental.compgraph.channel.collection.foreach.OpsCollectionForEach;
import experimental.compgraph.channel.collection.foreach.OpsNestedGridThatUsesAForEach;
import experimental.compgraph.channel.collection.nested.OpsNestedCollection;
import experimental.compgraph.channel.collection.nested.OpsNestedGrid;
import experimental.compgraph.channel.collection.rai.OpsRai;
import experimental.compgraph.channel.collection.tiling.ImgAlgebraTestDriven.MyMoreComplexFunction;

public class ImgAlgebraTestDriven {

	// FIXME
	// is there a nicer way to directly transform OpsCollection<T> to Grid<T>
	// etc.?! (reshape?).

	//

	public <T extends RealType<T> & NativeType<T>> void funWithImages() {

		// == Reference Workflow: ==

		// Input image
		final OpsRai<T> in = null;

		// Tile
		final OpsTiling<T> tiled = in.toTiling(null, null);

		// Gauss
		final OpsTiling<DoubleType> filtered = tiled.mapTile(new MyMoreComplexFunction<T, DoubleType>());

		// Calc histogram per tile
		final OpsGrid<Histogram1d<DoubleType>> histos = filtered.map((t) -> new Histogram1d<DoubleType>(
			(Histogram1d<DoubleType>) null));

		// Merge histograms
		final OpsElement<Histogram1d<DoubleType>> merged = histos.treeReduce((h1, h2) -> new Histogram1d<DoubleType>(
			(Histogram1d<DoubleType>) null));

		// Threshold from histogram
		final OpsElement<DoubleType> thresh = merged.map((h) -> new DoubleType());

		// Join filtered image and threshold:
		final OpsGrid<OpsGrid<Pair<DoubleType, DoubleType>>> joined = filtered.map((element) -> element.cartesian(thresh));

		// Apply threshold:
		final OpsGrid<OpsGrid<BitType>> applied = joined.map((tile) -> tile.map((p) -> new BitType(p.getA().get() >= p
			.getB().get())));

		// Some helper to view as tiling again? (or let OpsTiling.cartesianEach(..) produce a tiling in the first place)
		// final OpsTiling<BitType> applied2 = toTiling(applied);

		// TODO: CCA & Feature Extraction

		// ====

		final OpsCollection<OpsRai<T>> rais = null;

		final OpsChannel<OpsChannel<T>> map3 = rais.map((rai) -> rai.toRai((rai2) -> rai2));

		// == Some test of the foreach-approach: ==
//
//		final OpsNestedGridThatUsesAForEach<T, OpsCollection<T>, OpsCollectionForEach<T>> tiling = null;
//
//		// what we want to replace:
//		tiling.mapEach((pixel) -> pixel);
//		// which equals:
//		tiling.map((tile) -> tile.map((pixel) -> pixel));
//		// approach:
//		tiling.forEach().map((pixel) -> pixel).endForEach();

		// ====

		// TODO: flatten on nested
		final OpsCollection<OpsElement<Histogram1d<DoubleType>>> out3 = histos.map((tiling) -> tiling.treeReduce((a,
			b) -> new Histogram1d<DoubleType>((Histogram1d<DoubleType>) null)));

		final OpsCollection<OpsElement<DoubleType>> out4 = out3.map((e) -> e.map((h) -> new DoubleType(0)));

		final OpsCollection<OpsGrid<Pair<OpsRai<DoubleType>, OpsElement<DoubleType>>>> out5 = out1.map((t) -> t.cartesian(
			out4));

//		OpsCollection<OpsTilingCollection<T>> map = in.tile(null, null).mapTile((tile) -> tile.tile(null, null));

		final OpsCollection<OpsTilingCollection<T>> map = in.tile(null, null).map((tiling) -> tiling.tile(null, null));

		final OpsTilingCollection<T> tile = in.tile(null, null);

		// out5.map((img, thresh)

		// final OpsCollection<OpsGrid<OpsRAI<T>>> sliced = in.map((f) -> (OpsGrid<OpsRAI<T>>) null);

		/* 1: TODO partition, do something and merge back */

		// TODO can we make sure that this is also an OpsRAI<T> again?! Do we
		// need mapTile?
		final OpsCollection<OpsGrid<OpsRAI<DoubleType>>> tiling = sliced.map((o) -> o.map(
			new MyMoreComplexFunction<T, DoubleType>()));

		/* 2: TODO parallelize and do not merge back (groupby) */
		final OpsCollection<Double> map2 = in.group((f) -> 0).map((f) -> 8.0d);

		/* 4: TODO pairs */

		/* 5: TODO neighbors */

		/* 6: TODO filter */

		/* 7: TODO pixels: has to be evaluate lazy locally */

		/* 8: Mean per x,y plane in video */
		// each XY plane

		/* 9: Work on Distributed over time */
		final OpsCollection<OpsRAI<T>> grid = null;

		// if someone can visualize this LAZY graphy he is genious.
		// OpsBoundedStream<OpsTiling<T>> stream = tiled.stream();

		/**
		 * 10: partition over Z and do gauss in 2D X,Y and then partition over X to do gauss in Z,Y
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
		 *
		 * Remark from one day later: I added `reshape` on grid for logical
		 * tiling. Additionally, I added `NestedGridRAI` which is pure
		 * syntactical sugar. Feel free to remove this again, however, I think
		 * it's quite useful. Important: `reshape` also indicates parallel
		 * independence, but in contrast to tiling, this is a logical operation.
		 *
		 * The user should never choose a tiling operation on his own. we should
		 * do that. He should however, provide hints and provide the information
		 * what he would do in a tiled world. Therefore we separate tiling and
		 * reshape.
		 *
		 * One day later: I added a structure for OpsList<OpsRAI<T>>. this
		 * allows fancy, logical nesting (forget about reshape ;-)).
		 *
		 */

		final OpsRAI<DoubleType> first = in.slice(0, 1).mapRAI(new MyMoreComplexFunction<T, DoubleType>()).flatten();
		final OpsRAI<DoubleType> second = in.slice(0, 1).mapRAI(new MyMoreComplexFunction<T, DoubleType>()).flatten();
		final OpsRAI<DoubleType> third = in.slice(0, 1).mapRAI(new MyMoreComplexFunction<T, DoubleType>()).flatten();

		/**
		 * 11: Do something per pixel logically, but find a nice tiling for it
		 */

		// we shouldn't create each branch explicitly. we should rather create
		// tiles and use converters (imglib2) if possible :-)
		final OpsGrid<DoubleType> mapped = in.map(new MyHybridSimpleMapper<T, DoubleType>());

		/**
		 * 12: Zernike
		 */
		final NestedOpsRAI<OpsRAI<T>> coll = null;

		// this is fishy, we should have a OpsCollection<LabelRegion<String>>
		// here.
		final NestedOpsRAI<LabelingType<String>> maps = coll.mapRAI((i) -> ArrayImgs.bits(10)).mapRAI((
			i) -> (RandomAccessibleInterval<LabelingType<String>>) null);

		final OpsCollection<LabelRegion<String>> regions = maps.map((r, c) -> c.accept((LabelRegion<String>) null));
		final OpsList<LabelRegion<String>> fixOrder = regions.fixOrder();
		final OpsGrid<Pair<double[], LabelRegion<String>>> join = fixOrder.map((r) -> new double[5]).join(fixOrder);

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
		public void compute1(final I input, final O output) {
			// TODO Auto-generated method stub

		}

		@Override
		public O createOutput(final I input) {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public static class MyNeighborhoodFunction<I, O> extends
		AbstractUnaryFunctionOp<RandomAccessibleInterval<I>, RandomAccessibleInterval<O>>
	{

		@Override
		public RandomAccessibleInterval<O> compute1(final RandomAccessibleInterval<I> input) {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public static class MyComplexSlicer<I, O> extends
		AbstractUnaryFunctionOp<RandomAccessibleInterval<I>, RandomAccessibleInterval<O>>
	{

		@Override
		public RandomAccessibleInterval<O> compute1(final RandomAccessibleInterval<I> input) {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public static class MyMoreComplexFunction<I, O> extends
		AbstractUnaryFunctionOp<RandomAccessibleInterval<I>, RandomAccessibleInterval<O>>
	{

		@Override
		public RandomAccessibleInterval<O> compute1(final RandomAccessibleInterval<I> input) {
			return null;
		}
	}

	public static class MyMean<T> implements BiFunction<DoubleType, RandomAccessibleInterval<T>, DoubleType> {

		@Override
		public DoubleType apply(final DoubleType memo, final RandomAccessibleInterval<T> u) {
			return null;
		}

		public OpsTiling<O> getExecutionPlan(final OpsTiling<I> rai) {
			return
		}
	}

	public static class MyTiler<T> implements Function<RandomAccessibleInterval<T>, OpsTiling<T>> {

		@Override
		public OpsTiling<T> apply(final RandomAccessibleInterval<T> t) {
			// TODO Auto-generated method stub
			return null;
		}

	}
}
