
package experimental.compgraph.channels;

import net.imglib2.util.Pair;

public interface OpsList<I> extends OpsGrid<I> {

	// In theory we could simply call join internally... however, this would
	// suck in performance in the case we know we are joining a grid why
	// shouldn't we make use of this information?

	// TODO: How to handle joining of lists with uneven sizes? Special kind of
	// joins? related to joins in DB!
	<I2> OpsList<Pair<I, I2>> join(OpsList<I2> l);
}
