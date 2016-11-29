package experimental.cache;

import net.imglib2.Localizable;

import experimental.compgraph.channels.rai.OpsRai;

// tile only represents inner part of cropped image
public interface OpsTile<T> extends OpsRai<T>, Localizable {

	long[] getOverlap();

	// gets the entire tile
	OpsRai<T> getOverlapInterval();
}
