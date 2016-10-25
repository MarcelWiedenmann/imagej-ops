
package experimental.tiling.mapreduce;

import net.imglib2.Dimensions;

public interface TilableMapOp extends TilableOp {

	public default Dimensions getOverlap() {
		return null;
	}
}
