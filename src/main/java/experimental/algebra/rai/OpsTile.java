package experimental.algebra.rai;

import net.imglib2.Localizable;

// tile only represents inner part of cropped image
public interface OpsTile<T> extends OpsRAI<T>, Localizable {

	long[] getOverlap();

	// gets the entire tile
	OpsRAI<T> getOverlapInterval();
}
