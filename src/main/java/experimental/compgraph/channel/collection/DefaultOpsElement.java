
package experimental.compgraph.channel.collection;

import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import net.imglib2.util.Pair;

import experimental.compgraph.CompgraphOutputNode;
import experimental.compgraph.DataHandle;
import experimental.compgraph.channel.OpsBoundedChannel;

public class DefaultOpsElement<I> implements OpsElement<I> {

	private final CompgraphOutputNode<I, ? extends DataHandle<I, ?>> parent;

	public DefaultOpsElement(final CompgraphOutputNode<I, ? extends DataHandle<I, ?>> parent) {
		parent.setOutEdge(this);
		this.parent = parent;
	}

	// -- OpsElement --

	@Override
	public <I2> OpsElement<? extends Pair<I, I2>> join(final OpsBoundedChannel<I2> c,
		final BiPredicate<? super I, ? super I2> f)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <I2> OpsElement<? extends Pair<I, I2>> cartesian(final OpsBoundedChannel<I2> c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O> OpsElement<O> map(final Function<? super I, O> f) {
		return new DefaultOpsElement<>(parent.cgs().factory().map(this, f));
	}

	@Override
	public <O> OpsElement<O> map(final BiConsumer<? super I, ? extends Consumer<O>> f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OpsElement<I> filter(final Predicate<? super I> f) {
		return new DefaultOpsElement<>(parent.cgs().factory().filter(this, f));
	}

	@Override
	public <O> OpsElement<? extends OpsBoundedChannel<O>> partition(
		final BiConsumer<? super I, ? extends Consumer<O>> f)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O> OpsBoundedChannel<? extends OpsBoundedChannel<O>> group(final Function<? super I, Integer> f) {
		// TODO Auto-generated method stub
		return null;
	}

	// -- CompgraphSingleEdge --

	@Override
	public CompgraphOutputNode<I, ? extends DataHandle<I, ?>> parent() {
		return parent;
	}
}
