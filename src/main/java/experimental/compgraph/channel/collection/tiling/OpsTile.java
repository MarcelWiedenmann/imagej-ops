
package experimental.compgraph.channel.collection.tiling;

import net.imglib2.Localizable;

import experimental.compgraph.channel.collection.rai.OpsRai;

// tile only represents inner part of cropped image
public interface OpsTile<T> extends OpsRai<T>, Localizable {

	long[] getOverlap();

	// gets the entire tile - TODO: or change to getInnerInterval() and interpret OpsTile<T> as the entire tile?
	// (use case: chaining of multiple filters (= convolutions), entire interval (incl. overlap) needs to be processed)
	OpsRai<T> getOverlapInterval();
}
