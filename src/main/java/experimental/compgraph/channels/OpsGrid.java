
package experimental.compgraph.channels;

import java.util.function.Function;

import net.imglib2.util.Pair;

// TODO we may want to re-add OpsGrid to separate it from imglib2.
public interface OpsGrid<T> extends OpsCollection<T> {

	// TODO we need to take care that we go for OpsTiles -> OpsTiles whenever
	// possible.
	@Override
	<O> OpsGrid<O> map(Function<? super T, O> f);

	// TODO: How to handle joining of lists with uneven sizes? Special kind of
	// joins? related to joins in DB!
	// TODO for tilings we can then add OutOfBoundsStrategy! :-)
	<T2> OpsGrid<Pair<T, T2>> join(OpsGrid<T2> g2);

}
