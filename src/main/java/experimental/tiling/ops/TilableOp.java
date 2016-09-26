
package experimental.tiling.ops;

import net.imagej.ops.Op;
import net.imglib2.Dimensions;

import experimental.tiling.misc.Util;

public interface TilableOp extends Op {

	default Dimensions getOverlap() {
		return Util.ZeroDimensions;
	}
}
