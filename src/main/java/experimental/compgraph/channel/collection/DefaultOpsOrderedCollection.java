
package experimental.compgraph.channel.collection;

import java.util.Comparator;
import java.util.Iterator;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import net.imglib2.util.Pair;

import experimental.compgraph.CompgraphNode;
import experimental.compgraph.CompgraphSingleEdge;
import experimental.compgraph.Dataflow;
import experimental.compgraph.channel.OpsBoundedChannel;
import experimental.compgraph.channel.OpsChannel;
import experimental.compgraph.channel.stream.OpsBoundedStream;

public class DefaultOpsOrderedCollection<I> implements OpsOrderedCollection<I> {

	private final CompgraphNode<?, ?, DefaultOpsOrderedCollection<I>> source;

	public DefaultOpsOrderedCollection(final CompgraphNode<?, ?, DefaultOpsOrderedCollection<I>> source) {
		this.source = source;
	}

	@Override
	public OpsCollection<I> concat(final OpsCollection<I> c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O> OpsElement<O> reduce(final O memo, final BiFunction<O, ? super I, O> f, final BiFunction<O, O, O> merge) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OpsElement<I> treeReduce(final BiFunction<I, I, I> f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OpsOrderedCollection<I> fixOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OpsOrderedCollection<I> sort(final Comparator<I> f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OpsBoundedStream<I> stream() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O> OpsCollection<? extends OpsBoundedChannel<O>> group(final Function<? super I, Integer> f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O> OpsChannel<O> transform(final Function<? super OpsChannel<I>, OpsChannel<O>> f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CompgraphNode<?, ?, ? extends CompgraphSingleEdge<I>> source() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dataflow<I, ?> dataflow() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<I> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <I2> OpsOrderedCollection<Pair<I, I2>> join(final OpsOrderedCollection<I2> c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <I2> OpsOrderedCollection<Pair<I, I2>> join(final OpsBoundedChannel<I2> c,
		final BiPredicate<? super I, ? super I2> f)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <I2> OpsOrderedCollection<Pair<I, I2>> cartesian(final OpsBoundedChannel<I2> c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O> OpsOrderedCollection<O> map(final Function<? super I, O> f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O> OpsOrderedCollection<O> map(final BiConsumer<? super I, ? extends Consumer<O>> f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OpsOrderedCollection<I> filter(final Predicate<? super I> f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O> OpsOrderedCollection<? extends OpsBoundedChannel<O>> partition(
		final BiConsumer<? super I, ? extends Consumer<O>> f)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
