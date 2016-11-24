package experimental.algebra.rai;

import net.imglib2.Localizable;

import experimental.algebra.OpsRAI;

// tile itself is valid defined
public interface OpsTile<T> extends OpsRAI<T>, Localizable {

	long[] getOverlap();

	OpsRAI<T> getOverlapInterval();
}
