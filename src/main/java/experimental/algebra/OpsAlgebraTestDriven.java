package experimental.algebra;

import net.imagej.ops.filter.gauss.DefaultGaussRAI;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.img.array.ArrayImgs;
import net.imglib2.roi.labeling.ImgLabeling;
import net.imglib2.roi.labeling.LabelingType;
import net.imglib2.type.NativeType;
import net.imglib2.type.numeric.RealType;

import experimental.algebra.rai.OpsTile;

public class OpsAlgebraTestDriven<T extends RealType<T> & NativeType<T>> {

	public void project() {
		// usecase 1: projection
		OpsRAI<T> bigImg = null;

		// here I have C<C<T>> where inner C<T> is a tile!
		OpsRAI<Object> merge = bigImg.partition(() -> new long[] { 1, 1, 512 }).map((ci) -> ci.aggregate((a, b) -> 5));
	}

	public void fun() {
		OpsRAI<T> grid = null;
		OpsCollection<T> in = null;

		/* 1: parallelize and merge back */
		OpsCollection<T> merged = in.
				// distribute in parallel collections
				scatter((t) -> 15).
				// map C<T> -> C<T> for inner collection elements
				map((t) -> t).
				// merge back
				merge();

		/* 2: TODO parallelize and do not merge back (groupby) */
		OpsCollectionNested<T> scatter = in.<T> scatter((f) -> 15);

		/* 3: TODO tiling, gauss and back */
		OpsCollection<RandomAccessibleInterval<T>> result = grid.partition((f) -> ((OpsTile<T>) null))
				.scatter((f) -> 15).forEach(new DefaultGaussRAI<T>()).merge();
				// how to get back image
				// OpsGrid<T> reduced = result.reduce(memo, f)

		/* 4: TODO pairs */

		/* 5: TODO neighbors */

		/* 6: TODO filter */

		/* 7: TODO pixels: has to be evaluate lazy locally */

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

	// TODO recursion?
	public void kmeans() {
		// usecase 2: K-Means
		OpsList<Row> rows = null;
		OpsList<Row> cluster = null;

		OpsCollectionNested<Row> scatter = rows
				.scatter((r) -> 15 /* distribute to workers */);

		OpsCollectionNested<KMeansPair> pairs = scatter
				.map((c) -> c.cartesian(cluster).map(p -> new KMeansPair(p.getA(), p.getB())).reduce(KMeansPair.memo(),
						(m, p) -> m.getDist() < p.getDist() ? m : p))
				.merge().scatter((r) -> 10 /* distribute over cluster-centers */);

		OpsCollection<Row> clusterCenters = pairs
				.map((c) -> c.reduce(new DistMean(), (DistMean m, KMeansPair p) -> m.accept(p.getMe()))).merge()
				.map((dm) -> dm.mean());
	}

	static class DistMean {
		double[] sum;
		double[] count;

		public DistMean() {
		}

		public DistMean accept(Row dist) {
			return this;
		}

		public Row mean() {
			return null;
		}
	}

	// TODO this is actually exactly what we expect from a table ;-)
	static class KMeansPair {
		private Row me;
		private Row cluster;
		private double dist;

		public static KMeansPair memo() {
			// singleton
			return new KMeansPair();
		}

		private KMeansPair() {
			this.me = null;
			this.cluster = null;
			this.dist = Double.MAX_VALUE;
		}

		public KMeansPair(Row me, Row cluster) {
			this.me = me;
			this.cluster = cluster;
			this.dist = me.distance(cluster);
		}

		public Row getMe() {
			return me;
		}

		public Row getCluster() {
			return cluster;
		}

		public double getDist() {
			return dist;
		}

	}

	static class Row {
		double distance(Row r) {
			return 0;
		}
	}
}
