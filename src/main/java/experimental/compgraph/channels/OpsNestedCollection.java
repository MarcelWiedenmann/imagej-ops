
package experimental.compgraph.channels;

import java.util.function.BiFunction;
import java.util.function.Function;

public interface OpsNestedCollection<T, C extends OpsChannel<T>> extends OpsNestedChannel<T, C>, OpsCollection<C> {

	<O> OpsNestedCollection<O, C> mapEach(Function<? super T, O> f);

	OpsCollection<T> treeReduceEach(BiFunction<T, T, T> f);

	<O> OpsCollection<O> reduceEach(O memo, BiFunction<O, ? super T, O> f, BiFunction<O, O, O> merge);

}
