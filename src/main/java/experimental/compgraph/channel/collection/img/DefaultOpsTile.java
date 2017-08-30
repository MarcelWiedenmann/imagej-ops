
package experimental.compgraph.channel.collection.img;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import net.imglib2.AbstractInterval;
import net.imglib2.Interval;
import net.imglib2.RandomAccess;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.algorithm.neighborhood.Shape;
import net.imglib2.converter.Converter;
import net.imglib2.util.Pair;

import experimental.compgraph.CompgraphOutputNode;
import experimental.compgraph.DataHandle;
import experimental.compgraph.channel.OpsBoundedChannel;
import experimental.compgraph.channel.collection.OpsCollection;
import experimental.compgraph.channel.collection.OpsElement;
import experimental.compgraph.channel.collection.OpsGrid;
import experimental.compgraph.channel.collection.OpsOrderedCollection;
import experimental.compgraph.channel.stream.OpsBoundedStream;

public class DefaultOpsTile<I> extends AbstractInterval implements OpsTile<I> {

	private final RandomAccessibleInterval<I> inner;

	public DefaultOpsTile(final RandomAccessibleInterval<I> inner) {
		super(inner);
		this.inner = inner;
	}

	@Override
	public OpsTiling<I> toTiling(final long[] tilesPerDim, final long[] overlap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OpsGrid<? extends OpsIterableInterval<I>> neighbors(final Shape s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <I2> OpsOrderedCollection<? extends Pair<I, I2>> join(final OpsOrderedCollection<I2> c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OpsCollection<I> concat(final OpsCollection<I> c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O> OpsElement<O> reduce(final O memo, final BiFunction<O, ? super I, O> f, final BinaryOperator<O> merge) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OpsElement<I> treeReduce(final BinaryOperator<I> f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OpsOrderedCollection<I> sort(final Comparator<? super I> f) {
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
	public CompgraphOutputNode<I, ? extends DataHandle<I, ?>> parent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<CompgraphOutputNode<?, ?>> parents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<I> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RandomAccess<I> randomAccess() {
		return inner.randomAccess();
	}

	@Override
	public RandomAccess<I> randomAccess(final Interval interval) {
		return inner.randomAccess(interval);
	}

	@Override
	public long[] getOverlap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OpsRai<I> getOverlapInterval() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O> OpsTile<O> toRai(final Function<? super OpsRai<I>, ? extends RandomAccessibleInterval<O>> f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O> OpsTile<O> toRai(final Converter<? super I, O> c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OpsTile<I> interval(final Interval i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OpsTile<I> subsample(final long... steps) {
		// TODO Auto-generated method stub
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
	public <O> OpsTile<O> map(final Function<? super I, O> f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O> OpsTile<O> map(final BiConsumer<? super I, ? extends Consumer<O>> f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OpsIterableInterval<I> filter(final Predicate<? super I> f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O> OpsTile<? extends OpsBoundedChannel<O>> partition(final BiConsumer<? super I, ? extends Consumer<O>> f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OpsTile<I> fixOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void localize(final int[] position) {
		// TODO Auto-generated method stub

	}

	@Override
	public void localize(final long[] position) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getIntPosition(final int d) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getLongPosition(final int d) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void localize(final float[] position) {
		// TODO Auto-generated method stub

	}

	@Override
	public void localize(final double[] position) {
		// TODO Auto-generated method stub

	}

	@Override
	public float getFloatPosition(final int d) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getDoublePosition(final int d) {
		// TODO Auto-generated method stub
		return 0;
	}
}
