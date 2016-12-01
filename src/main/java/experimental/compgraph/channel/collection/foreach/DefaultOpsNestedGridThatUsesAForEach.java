
package experimental.compgraph.channel.collection.foreach;

import java.util.Comparator;
import java.util.Iterator;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import net.imglib2.Interval;
import net.imglib2.Positionable;
import net.imglib2.RandomAccess;
import net.imglib2.RealPositionable;
import net.imglib2.util.Pair;

import experimental.compgraph.channel.OpsChannel;
import experimental.compgraph.channel.collection.OpsCollection;
import experimental.compgraph.channel.collection.OpsElement;
import experimental.compgraph.channel.collection.OpsGrid;
import experimental.compgraph.channel.collection.OpsList;
import experimental.compgraph.channel.collection.nested.OpsNestedChannel;
import experimental.compgraph.channel.collection.nested.OpsNestedGrid;
import experimental.compgraph.channel.stream.OpsBoundedStream;

public class DefaultOpsNestedGridThatUsesAForEach<T, C extends OpsCollection<T>, CF extends OpsCollectionForEach<T, CF>>
	implements OpsNestedGridThatUsesAForEach<T, C, CF>
{

	CF innerType;

	// TODO: how to store and keep track of our inner elements? how to synchronize between them and our innerType? (or
	// just store them in the inner type?, would be the cleanest...)

	// TODO: we have to create an instance of CF to pass to this constructor; so CF probably needs an empty
	// constructor (chicke-egg) - this is uhugly as s**t :D maybe introduce some factory pattern because why not
	public DefaultOpsNestedGridThatUsesAForEach(final CF innerType) {
		this.innerType = innerType;
	}

	@Override
	public CF forEach() {
		return innerType.createFromContainer(this);
	}

	@Override
	public <O, CO extends OpsCollection<O>, CFO extends OpsCollectionForEach<O, CFO>>
		OpsNestedGridThatUsesAForEach<O, CO, CFO> createFromForEach(final CFO innerType)
	{
		return new DefaultOpsNestedGridThatUsesAForEach<O, CO, CFO>(innerType);
	}

	// -- --

	@Override
	public OpsNestedGrid<T, C> interval(final Interval i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OpsNestedGrid<T, C> subsample(final long... steps) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <I2> OpsGrid<? extends OpsChannel<? extends Pair<T, I2>>> cartesianEach(final OpsCollection<I2> c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OpsGrid<T> treeReduceEach(final BiFunction<? super T, ? super T, T> f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O> OpsGrid<O> reduceEach(final O memo, final BiFunction<O, ? super T, O> f,
		final BiFunction<? super O, ? super O, O> merge)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O, CC extends OpsChannel<O>> OpsNestedGrid<O, CC> mapEach(final BiConsumer<? super T, Consumer<O>> f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O, CC extends OpsChannel<O>> OpsNestedGrid<O, CC> mapEach(final Function<? super T, O> f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OpsCollection<T> flatten() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O, CC extends OpsChannel<O>> OpsNestedChannel<O, CC> filterEach(final Predicate<? super T> f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OpsCollection<C> filter(final Predicate<? super C> f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O> OpsElement<O> reduce(final O memo, final BiFunction<O, ? super C, O> f, final BiFunction<O, O, O> merge) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O> OpsElement<O> treeReduce(final BiFunction<O, O, O> aggregate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OpsCollection<C> concat(final OpsCollection<C> c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <I2> OpsCollection<? extends Pair<C, I2>> join(final OpsCollection<I2> c, final BiPredicate<C, I2> f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OpsList<C> fixOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OpsList<C> sort(final Comparator<C> f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OpsBoundedStream<C> stream() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<C> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O> OpsGrid<O> map(final Function<? super C, O> f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O> OpsGrid<O> map(final BiConsumer<? super C, Consumer<O>> f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <I2> OpsGrid<? extends Pair<C, I2>> cartesian(final OpsCollection<I2> c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RandomAccess<C> randomAccess() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RandomAccess<C> randomAccess(final Interval interval) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int numDimensions() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long min(final int d) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void min(final long[] min) {
		// TODO Auto-generated method stub

	}

	@Override
	public void min(final Positionable min) {
		// TODO Auto-generated method stub

	}

	@Override
	public long max(final int d) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void max(final long[] max) {
		// TODO Auto-generated method stub

	}

	@Override
	public void max(final Positionable max) {
		// TODO Auto-generated method stub

	}

	@Override
	public double realMin(final int d) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void realMin(final double[] min) {
		// TODO Auto-generated method stub

	}

	@Override
	public void realMin(final RealPositionable min) {
		// TODO Auto-generated method stub

	}

	@Override
	public double realMax(final int d) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void realMax(final double[] max) {
		// TODO Auto-generated method stub

	}

	@Override
	public void realMax(final RealPositionable max) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dimensions(final long[] dimensions) {
		// TODO Auto-generated method stub

	}

	@Override
	public long dimension(final int d) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <O, C2 extends OpsCollection<O>> OpsCollection<C2> partition(final Function<? super C, C2> f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O, C2 extends OpsCollection<O>> OpsCollection<C2> group(final Function<? super C, Integer> f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <I2, C2 extends OpsGrid<I2>> OpsGrid<Pair<C, I2>> join(final C2 g) {
		// TODO Auto-generated method stub
		return null;
	}
}
