
package experimental.compgraph.channels;

import java.util.function.BiFunction;

import net.imglib2.Interval;
import net.imglib2.util.Pair;

public interface OpsListCollection<T, C extends OpsList<T>> extends OpsCollection<C> {

	// TODO: introduce OpsCollectionCollection, nested?
	OpsCollection<OpsCollection<Pair<T, T>>> pairs(/* TODO: we need some iterator over neighborhood*/);

	OpsListCollection<T, C> interval(Interval i);

	OpsListCollection<T, C> subsample(long... steps);

	@Override
	default <O> OpsElement<O> reduce(final O memo, final BiFunction<O, ? super C, O> f, final BiFunction<O, O, O> merge) {
		// TODO Auto-generated method stub
		return null;
	}
}
