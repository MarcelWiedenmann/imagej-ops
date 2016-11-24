package experimental.algebra.rai;

import net.imagej.ops.filter.gauss.DefaultGaussRAI;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.img.array.ArrayImgs;
import net.imglib2.roi.labeling.ImgLabeling;
import net.imglib2.roi.labeling.LabelingType;
import net.imglib2.type.NativeType;
import net.imglib2.type.numeric.RealType;

import experimental.algebra.OpsCollection;
import experimental.algebra.OpsCollectionNested;

public class ImgAlgebraTestDriven {

	public <T extends RealType<T> & NativeType<T>> void funWithImages() {
		OpsCollection<T> in = null;

		/* 1: parallelize and merge back */

		/* 2: TODO parallelize and do not merge back (groupby) */

		/* 3: TODO tiling, gauss and back */

		/* 4: TODO pairs */

		/* 5: TODO neighbors */

		/* 6: TODO filter */

		/* 7: TODO pixels: has to be evaluate lazy locally */

		/* 8: TODO RAI inputs */
		OpsCollection<OpsGrid<T>> c = null;
		OpsCollectionNested<OpsGrid<T>> scattered = c.scatter((f) -> 14);

		/* 9: TODO Mean per x,y plane in video */

		/* 10: TODO */
		// here I have C<C<T>> where inner C<T> is a tile!
		OpsRAINested<Integer> merge = bigImg.partition(() -> new long[] { 1, 1, 512 })
				.map((ci) -> ci.aggregate((a, b) -> 5));

	}

	// TODO
	public void ccaWithFilter() {

		OpsRAI<T> bigImg = null;
		final long[] tileSize = null;

		OpsRAI<RandomAccessibleInterval<LabelingType<String>>> cca = bigImg.partition(() -> tileSize)
				.map(new DefaultGaussRAI<T>()).map((t) -> ArrayImgs.bits(1, 2, 3)).map((t) -> new ImgLabeling<>(null));

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
}
