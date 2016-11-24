
package experimental.algebra;

import java.util.function.Function;

public interface OpsElement<I> extends OpsList<I> {

	@Override
	<O> OpsElement<O> map(Function<? super I, O> f);

	@Override
	default <O, C extends OpsCollection<O>> OpsElement<C> partition(final Function<? super I, C> f) {
		return map(f);
	}

}
