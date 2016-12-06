
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

// TODO: Compare method signatures of our primitives and Java Stream primitives. E.g:
// - Java's 'sort' expects a 'Comparator<? super I>' instead of our 'Comparator<I>'.
// - Java's 'reduce' expects a 'BinaryOperator<O>' instead of our 'BiFunction<O,O,O>'.
// ...

public class DefaultOpsCollection<I> implements OpsCollection<I> {

	private final CompgraphNode<?, ?, DefaultOpsCollection<I>> source;

	public DefaultOpsCollection(final CompgraphNode<?, ?, DefaultOpsCollection<I>> source) {
		source.setOutput(this);
		this.source = source;
	}

	// -- OpsCollection --

	@Override
	public <O> OpsChannel<O> transform(final Function<? super OpsChannel<I>, OpsChannel<O>> f) {
		return f.apply(this);
	}

	@Override
	public Iterator<I> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OpsCollection<I> concat(final OpsCollection<I> c) {
		// TODO: Stream.concat(source, c); // We somehow need to expose the inner Java Stream ('source').
		return null;
	}

	@Override
	public <O> OpsElement<O> reduce(final O memo, final BiFunction<O, ? super I, O> f, final BiFunction<O, O, O> merge) {
//		return new DefaultOpsElement<>(source.factory().reduce(this, memo, f, merge));
		return null;
	}

	@Override
	public OpsElement<I> treeReduce(final BiFunction<I, I, I> f) {
		// TODO: How does this work? (BiFunction<O,O,O>)
		return null;
	}

	@Override
	public OpsOrderedCollection<I> fixOrder() {
		// TODO: How? Java Streams only know "sorted vs. unordered".

		return null;
	}

	@Override
	public OpsOrderedCollection<I> sort(final Comparator<I> f) {
//		return new DefaultOpsOrderedCollection<>(source.sorted(f));
		return null;
	}

	@Override
	public OpsBoundedStream<I> stream() {
//		return new DefaultOpsBoundedStream<>(source);
		return null;
	}

	@Override
	public <I2> OpsCollection<Pair<I, I2>> join(final OpsBoundedChannel<I2> c,
		final BiPredicate<? super I, ? super I2> f)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <I2> OpsCollection<Pair<I, I2>> cartesian(final OpsBoundedChannel<I2> c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O> OpsCollection<O> map(final Function<? super I, O> f) {
		return new DefaultOpsCollection<>(source.factory().map(this, f));
	}

	@Override
	public <O> OpsCollection<O> map(final BiConsumer<? super I, ? extends Consumer<O>> f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OpsCollection<I> filter(final Predicate<? super I> f) {
//		return new DefaultOpsCollection<>(source.filter(f));
		return null;
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

	@Override
	public CompgraphNode<?, ?, ? extends CompgraphSingleEdge<I>> source() {
		return source;
	}

	@Override
	public Dataflow<I, ?> dataflow() {
		// TODO: Pull mechanism!
		return null;
	}
}
