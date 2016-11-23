package experimental.algebra.img;

import net.imglib2.Localizable;

import experimental.algebra.OpsGrid;

// tile itself is valid defined
public interface OpsTile<T> extends OpsGrid<T>, Localizable {

	long[] getOverlap();

	OpsGrid<T> getOverlapInterval();
}
