package experimental.compgraph.channel.collection.tiling;

import net.imglib2.Localizable;

import experimental.compgraph.channel.collection.OpsRai;

// tile only represents inner part of cropped image
public interface OpsTile<T> extends OpsRai<T>, Localizable {

	long[] getOverlap();

	// gets the entire tile
	OpsRai<T> getOverlapInterval();
}
