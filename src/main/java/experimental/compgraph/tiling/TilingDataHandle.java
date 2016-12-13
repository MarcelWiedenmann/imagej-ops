
package experimental.compgraph.tiling;

import net.imglib2.RandomAccessibleInterval;

import experimental.compgraph.DataHandle;
import experimental.compgraph.request.RequestableRai;

public interface TilingDataHandle<IO> extends DataHandle<RandomAccessibleInterval<IO>, RequestableRai<IO>> {

	// NB: Marker interface for data handles between tiling nodes.
}
