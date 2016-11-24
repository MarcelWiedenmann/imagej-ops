//package experimental.algebra.rai;
//
//import java.util.function.Function;
//
//import experimental.algebra.OpsCollection;
//
//public interface OpsRAIList<T> extends OpsRAI<OpsRAI<T>> {
//
//	@Override
//	<O, C extends OpsCollection<O>> OpsRAINested<C> partition(Function<? super OpsRAI<T>, C> f);
//
//	default OpsTiling<T> partition(TilingHints hints) {
//		partition(new TilingPartitioner<T>(hints));
//		return this.partition();
//	}
//
//	/**
//	 * Helpers
//	 */
//	public static interface TilingHints {
//		long[] getPartitioningHints();
//	}
//
//	public static class TilingPartitioner<T> implements Function<OpsRAI<T>, OpsTiling<T>> {
//
//		@SuppressWarnings("unused")
//		private TilingHints hints;
//
//		public TilingPartitioner(TilingHints hints) {
//			this.hints = hints;
//		}
//
//		@Override
//		public OpsTiling<T> apply(OpsRAI<T> t) {
//			return null;
//		}
//	}
//}
