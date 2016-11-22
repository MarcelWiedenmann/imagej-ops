
package experimental.algebra;

import net.imagej.ops.special.function.UnaryFunctionOp;
import net.imglib2.RandomAccessibleInterval;

public interface OpsRAI<T> extends OpsList<T>, RandomAccessibleInterval<T> {

	default <O> OpsRAI<O> pixelwise(final UnaryFunctionOp<T, O> f) {
		return this.map(f);
	}

	OpsTiling<T> partition(/* TilingHints */);

	// -- OpsList --

	// ...
}
