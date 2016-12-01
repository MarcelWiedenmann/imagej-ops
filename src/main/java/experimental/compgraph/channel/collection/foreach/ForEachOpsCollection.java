
package experimental.compgraph.channel.collection.foreach;

import java.util.Comparator;
import java.util.Iterator;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import net.imglib2.util.Pair;

import experimental.compgraph.channel.collection.OpsCollection;
import experimental.compgraph.channel.collection.OpsElement;
import experimental.compgraph.channel.collection.OpsList;
import experimental.compgraph.channel.collection.nested.OpsNestedCollection;
import experimental.compgraph.channel.stream.OpsBoundedStream;

public class ForEachOpsCollection<T> implements OpsCollection<T>, OpsCollectionForEach<T, ForEachOpsCollection<T>> {

	private final OpsNestedCollection<?, ? extends OpsCollection<?>> container;
	private final OpsCollection<? extends OpsCollection<T>> elements;

	public ForEachOpsCollection(final OpsNestedCollection<T, ? extends OpsCollection<T>> container) {
		this.container = container;
		this.elements = container;
	}

	private ForEachOpsCollection(final OpsNestedCollection<?, ? extends OpsCollection<?>> container,
		final OpsCollection<OpsCollection<T>> elements)
	{
		this.container = container;
		this.elements = elements;
	}

//	public NESTEDTYPE nest() {
//		return nested collections again
//	}

	@Override
	public ForEachOpsCollection<T> createFromContainer(
		final OpsNestedCollection<T, ? extends OpsCollection<T>> container)
	{
		// TODO Auto-generated method stub
		return null;
	}

	// -- --

	@Override
	public /*ForEach*/Iterator<T> iterator() {
		final OpsCollection<Iterator<T>> mappedElements = elements.map((elem) -> elem.iterator());
		// TODO
		return null;
	}

	@Override
	public <O> ForEachOpsCollection<O> map(final Function<? super T, O> f) {
		final OpsCollection<OpsCollection<O>> mappedElements = elements.map((elem) -> elem.map(f));
		return new ForEachOpsCollection<>(container, mappedElements);

	}

	@Override
	public <O> ForEachOpsCollection<O> map(final BiConsumer<? super T, Consumer<O>> f) {
		final OpsCollection<OpsCollection<O>> mappedElements = elements.map((elem) -> elem.map(f));
		return new ForEachOpsCollection<>(container, mappedElements);
	}

	@Override
	public ForEachOpsCollection<T> filter(final Predicate<? super T> f) {
		final OpsCollection<OpsCollection<T>> mappedElements = elements.map((elem) -> elem.filter(f));
		return new ForEachOpsCollection<>(container, mappedElements);
	}

	@Override
	public <O> /*ForEach*/OpsElement<O> reduce(final O memo, final BiFunction<O, ? super T, O> f,
		final BiFunction<O, O, O> merge)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O> /*ForEach*/OpsElement<O> treeReduce(final BiFunction<O, O, O> aggregate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ForEachOpsCollection<T> concat(final OpsCollection<T> c) {
		final OpsCollection<OpsCollection<T>> mappedElements = elements.map((elem) -> elem.concat(c));
		return new ForEachOpsCollection<>(container, mappedElements);
	}

	@Override
	public <I2> ForEachOpsCollection<Pair<T, I2>> join(final OpsCollection<I2> c, final BiPredicate<T, I2> f) {
		// FIXME
		final OpsCollection<OpsCollection<Pair<T, I2>>> mappedElements = elements.map((
			elem) -> (OpsCollection<Pair<T, I2>>) elem.join(c, f));
		return new ForEachOpsCollection<Pair<T, I2>>(container, mappedElements);
	}

	@Override
	public <I2> ForEachOpsCollection<? extends Pair<T, I2>> cartesian(final OpsCollection<I2> c) {
		// FIXME
		final OpsCollection<OpsCollection<Pair<T, I2>>> mappedElements = elements.map((
			elem) -> (OpsCollection<Pair<T, I2>>) elem.cartesian(c));
		return new ForEachOpsCollection<Pair<T, I2>>(container, mappedElements);
	}

	@Override
	public <O, C extends OpsCollection<O>> ForEachOpsCollection<C> partition(final Function<? super T, C> f) {
		final OpsCollection<OpsCollection<C>> mappedElements = elements.map((elem) -> elem.partition(f));
		return new ForEachOpsCollection<>(container, mappedElements);
	}

	@Override
	public <O, C extends OpsCollection<O>> ForEachOpsCollection<C> group(final Function<? super T, Integer> f) {
		final OpsCollection<OpsCollection<C>> mappedElements = elements.map((elem) -> elem.group(f));
		return new ForEachOpsCollection<>(container, mappedElements);
	}

	@Override
	public /*ForEach*/OpsList<T> fixOrder() {
		final OpsCollection<OpsList<T>> mappedElements = elements.map((elem) -> elem.fixOrder());
		// TODO
		return null;
	}

	@Override
	public /*ForEach*/OpsList<T> sort(final Comparator<T> f) {
		final OpsCollection<OpsList<T>> mappedElements = elements.map((elem) -> elem.sort(f));
		// TODO
		return null;
	}

	@Override
	public /*ForEach*/OpsBoundedStream<T> stream() {
		final OpsCollection<OpsBoundedStream<T>> mappedElements = elements.map((elem) -> elem.stream());
		// TODO
		return null;
	}
}
