
package experimental.algebra;

import java.util.Comparator;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import net.imglib2.util.Pair;

import experimental.compgraph.CompgraphNode;
import experimental.compgraph.CompgraphNodeBody;
import experimental.compgraph.CompgraphNodeFactory;
import experimental.compgraph.CompgraphSingleEdge;
import experimental.compgraph.algebra.Map;
import experimental.compgraph.algebra.Reduce;

public class DefaultOpsCollection<I> implements OpsCollection<I> {

	private final CompgraphNode<?, ? extends CompgraphNodeBody<?, OpsCollection<I>>, OpsCollection<I>> source;

	public DefaultOpsCollection(
		final CompgraphNode<?, ? extends CompgraphNodeBody<?, OpsCollection<I>>, OpsCollection<I>> source)
	{
		this.source = source;
	}

	@Override
	public
		CompgraphNode<?, ? extends CompgraphNodeBody<?, ? extends CompgraphSingleEdge<I>>, ? extends CompgraphSingleEdge<I>>
		source()
	{
		return source;
	}

	@Override
	public <O> OpsCollection<O> map(final Function<? super I, O> f) {
		final CompgraphNode<OpsCollection<I>, ? extends Map<OpsCollection<I>, I, O, OpsCollection<O>>, OpsCollection<O>> map =
			CompgraphNodeFactory.map(this, f);
		map.setOutput(new DefaultOpsCollection<O>(map));
		return map.out();
	}

	@Override
	public <O> OpsCollection<O> map(final BiConsumer<? super I, Consumer<O>> f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OpsCollection<I> filter(final Predicate<? super I> f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O> OpsCollection<O> reduce(final O memo, final BiFunction<O, ? super I, O> f,
		final BiFunction<O, O, O> merge)
	{
		final CompgraphNode<OpsCollection<I>, ? extends Reduce<OpsCollection<I>, I, O, OpsCollection<O>>, OpsCollection<O>> reduce =
			CompgraphNodeFactory.reduce(this, memo, f, merge);
		reduce.setOutput(new DefaultOpsCollection<>(reduce));
		return reduce.out();
	}

	@Override
	public <O> OpsCollection<O> treeReduce(final BiFunction<O, O, O> aggregate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OpsCollection<I> concat(final OpsCollection<I> c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <I2> OpsCollection<Pair<I, I2>> join(final OpsCollection<I2> c, final BiPredicate<I, I2> f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <I2> OpsCollection<Pair<I, I2>> cartesian(final OpsCollection<I2> c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OpsList<I> sort(final Comparator<I> f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OpsBoundedStream<I> stream() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O, C extends OpsCollection<O>> OpsCollection<C> partition(final Function<? super I, C> func) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O, C extends OpsCollection<O>> OpsCollection<C> group(final Function<? super I, Integer> func) {
		// TODO Auto-generated method stub
		return null;
	}
}
