
package experimental.compgraph.channel.collection;

import java.util.Comparator;
import java.util.Iterator;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import net.imglib2.util.Pair;

import experimental.compgraph.CompgraphOutputNode;
import experimental.compgraph.DataHandle;
import experimental.compgraph.channel.OpsBoundedChannel;
import experimental.compgraph.channel.stream.OpsBoundedStream;

public class DefaultOpsCollection<I> implements OpsCollection<I> {

	private final CompgraphOutputNode<I, ? extends DataHandle<I, ?>> parent;

	public DefaultOpsCollection(final CompgraphOutputNode<I, ? extends DataHandle<I, ?>> parent) {
		parent.setOutEdge(this);
		this.parent = parent;
	}

	// -- OpsCollection --

	@Override
	public OpsCollection<I> concat(final OpsCollection<I> c) {
		// TODO: Stream.concat(source, c); // We somehow need to expose the inner Java Stream.
		return null;
	}

	@Override
	public <O> OpsElement<O> reduce(final O memo, final BiFunction<O, ? super I, O> f, final BinaryOperator<O> merge) {
		return new DefaultOpsElement<>(parent.cgs().factory().reduce(this, memo, f, merge));
	}

	@Override
	public OpsElement<I> treeReduce(final BinaryOperator<I> f) {
		// TODO: How does this work? (BiFunction<O,O,O>)
		return null;
	}

	@Override
	public OpsOrderedCollection<I> fixOrder() {
		// TODO: How? Java Streams only know "sorted vs. unordered".

		return null;
	}

	@Override
	public OpsOrderedCollection<I> sort(final Comparator<? super I> f) {
//		return new DefaultOpsOrderedCollection<>(source.sorted(f));
		return null;
	}

	@Override
	public OpsBoundedStream<I> stream() {
//		return new DefaultOpsBoundedStream<>(source);
		return null;
	}

	@Override
	public <I2> OpsBoundedChannel<? extends Pair<I, I2>> join(final OpsBoundedChannel<I2> c,
		final BiPredicate<? super I, ? super I2> f)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <I2> OpsBoundedChannel<? extends Pair<I, I2>> cartesian(final OpsBoundedChannel<I2> c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O> OpsCollection<O> map(final Function<? super I, O> f) {
		return new DefaultOpsCollection<>(parent.cgs().factory().map(this, f));
	}

	@Override
	public <O> OpsCollection<O> map(final BiConsumer<? super I, ? extends Consumer<O>> f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OpsCollection<I> filter(final Predicate<? super I> f) {
		return new DefaultOpsCollection<>(parent.cgs().factory().filter(this, f));
	}

	@Override
	public <O> OpsCollection<? extends OpsBoundedChannel<O>> partition(
		final BiConsumer<? super I, ? extends Consumer<O>> f)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O> OpsCollection<? extends OpsBoundedChannel<O>> group(final Function<? super I, Integer> f) {
		// TODO Auto-generated method stub
		return null;
	}

	// -- Iterable --

	@Override
	public Iterator<I> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	// -- CompgraphSingleEdge --

	@Override
	public CompgraphOutputNode<I, ? extends DataHandle<I, ?>> parent() {
		return parent;
	}
}
