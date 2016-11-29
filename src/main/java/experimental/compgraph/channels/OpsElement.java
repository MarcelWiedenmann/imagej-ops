
package experimental.compgraph.channels;

import java.util.function.Function;

public interface OpsElement<I> extends OpsList<I> {

	/*
	 * -- Overrides --
	 */
	@Override
	<O> OpsElement<O> map(Function<? super I, O> f);
}
