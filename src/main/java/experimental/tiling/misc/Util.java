
package experimental.tiling.misc;

import net.imglib2.Dimensions;
import net.imglib2.FinalDimensions;
import net.imglib2.Interval;

public final class Util {

	public static Dimensions ZeroDimensions = new FinalDimensions(0);

	public static FinalDimensions copyDimensions(final Dimensions dims) {
		return FinalDimensions.wrap(Util.dimensionsToArray(dims));
	}

	public static long[] dimensionsToArray(final Dimensions dims) {
		final long[] dimsArr = new long[dims.numDimensions()];
		dims.dimensions(dimsArr);
		return dimsArr;
	}

	public static boolean equals(final Dimensions d1, final Dimensions d2) {
		if (d1.numDimensions() != d2.numDimensions()) return false;
		for (int d = 0; d < d1.numDimensions(); d++) {
			if (d1.dimension(d) != d2.dimension(d)) return false;
		}
		return true;
	}

	public static boolean isZeroMin(final Interval interval) {
		for (int d = 0; d < interval.numDimensions(); d++) {
			if (interval.min(d) != 0) return false;
		}
		return true;
	}
}
