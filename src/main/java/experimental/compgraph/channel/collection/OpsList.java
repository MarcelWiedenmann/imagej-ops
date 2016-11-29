
package experimental.compgraph.channel.collection;

import net.imglib2.util.Pair;

public interface OpsList<I> extends OpsGrid<I> {

	@Override
	<I2, C extends OpsGrid<I2>> OpsList<Pair<I, I2>> join(final C g2);
}
