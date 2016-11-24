package experimental.algebra.table;

import experimental.algebra.OpsCollection;
import experimental.algebra.DOpsCollection;
import experimental.algebra.OpsList;

public class TableAlgebraTestDriven {

	// TODO recursion?
		public void kmeans() {
			// usecase 2: K-Means
			OpsList<Row> rows = null;
			OpsList<Row> cluster = null;

			DOpsCollection<Row> scatter = rows
					.scatter((r) -> 15 /* distribute to workers */);

			DOpsCollection<KMeansPair> pairs = scatter
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
