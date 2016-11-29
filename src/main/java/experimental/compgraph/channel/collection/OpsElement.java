
package experimental.compgraph.channel.collection;

import java.util.function.Function;

import net.imglib2.util.Pair;

public interface OpsElement<I> extends OpsList<I> {

	@Override
	<O> OpsElement<O> map(Function<? super I, O> f);

	@Override
	<I2, C extends OpsGrid<I2>> OpsElement<Pair<I, I2>> join(final C g2);
}
