package experimental.algebra;

import net.imagej.ops.filter.gauss.DefaultGaussRAI;
import net.imglib2.FinalInterval;
import net.imglib2.IterableInterval;
import net.imglib2.img.array.ArrayImgs;
import net.imglib2.roi.Regions;
import net.imglib2.roi.labeling.ImgLabeling;
import net.imglib2.roi.labeling.LabelRegions;
import net.imglib2.roi.labeling.LabelingType;
import net.imglib2.type.NativeType;
import net.imglib2.type.logic.BitType;
import net.imglib2.type.numeric.RealType;

import experimental.algebra.img.OpsTile;
import experimental.algebra.img.OpsTiling;

public class OpsAlgebraTestDriven<T extends RealType<T> & NativeType<T>> {

	public void project() {
		// usecase 1: projection
		OpsGrid<T> bigImg = null;

		// here I have C<C<T>> where inner C<T> is a tile!
		OpsGrid<Object> merge = bigImg.partition(() -> new long[] { 1, 1, 512 })
				.collMap((ci) -> ci.aggregate((a, b) -> 5)).merge();
	}

	public void ccaWithFilter() {
		OpsGrid<T> bigImg = null;

		final long[] tileSize = null;

		OpsTiling<T, OpsTile<T>> partition = bigImg.partition(() -> tileSize);

		OpsTiling<T, OpsTile<T>> gauss = partition.mapTile(new DefaultGaussRAI<T>());

		OpsTiling<BitType, OpsTile<BitType>> thresholded = gauss.mapTile((t) -> ArrayImgs.bits(1, 2, 3));

		OpsTiling<LabelingType<String>, OpsTile<LabelingType<String>>> cca = thresholded
				.mapTile((t) -> new ImgLabeling<>(null));

		OpsGrid<LabelingType<String>> rai = cca.merge();

		OpsGrid<LabelingType<String>> filtered = rai.filter(new FinalInterval(5, 5));

		OpsTiling<LabelingType<String>, OpsTile<LabelingType<String>>> ccaPartition = filtered
				.partition(() -> tileSize);

		// option 1: use java to distribute via forEach on each partition
		OpsGridNested<Object, OpsCollection<Object>> allRegions = ccaPartition.collMap((l) -> l.map((t) -> new LabelRegions<String>(t)));

		// .map((p, e) -> p.getA().forEach((a) -> Regions.sample(a,
		// p.getB())).scatter((r) -> 15 /* depending on label we scatter
		// somewhere else. */)).

		// option 2: we distribute
		OpsCollection<IterableInterval<T>> distRegions = ccaPartition.map((l) -> new LabelRegions<>(l))
				.indexJoin(partition).map((p, e) -> p.getA().forEach((a) -> Regions.sample(a, p.getB())));

	}

	public void kmeans() {
		// usecase 2: K-Means
		OpsList<Row> rows = null;
		OpsList<Row> cluster = null;

		OpsCollectionNested<Row, OpsCollection<Row>> scatter = rows
				.scatter((r) -> 15 /* distribute to workers */);

		OpsCollectionNested<KMeansPair, OpsCollection<KMeansPair>> pairs = scatter
				.collMap((c) -> c.cartesian(cluster).map(p -> new KMeansPair(p.getA(), p.getB()))
						.reduce(KMeansPair.memo(), (m, p) -> m.getDist() < p.getDist() ? m : p))
				.merge().scatter((r) -> 10 /* distribute over cluster-centers */);

		OpsCollection<Row> clusterCenters = pairs
				.collMap((c) -> c.reduce(new DistMean(), (DistMean m, KMeansPair p) -> m.accept(p.getMe()))).merge()
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
