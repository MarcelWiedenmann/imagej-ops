package experimental.algebra.rai;

import java.util.function.Function;

import experimental.algebra.OpsGrid;

public interface OpsRAI<T> extends OpsGrid<T> {

	@Override
	<O> OpsRAI<O> map(Function<? super T, O> f);

	/**
	 * Special tiling stuff
	 */
	default DOpsTiling<T> tiling(@SuppressWarnings("unused") TilingHints... hints) {
		// internally calls SCATTER!!!!
		return null;
	}
}
