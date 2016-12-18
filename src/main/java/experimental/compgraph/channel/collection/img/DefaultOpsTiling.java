
package experimental.compgraph.channel.collection.img;

import java.util.Comparator;
import java.util.Iterator;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import net.imagej.ops.OpEnvironment;
import net.imagej.ops.special.function.AbstractUnaryFunctionOp;
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
import experimental.compgraph.node.Map;
import experimental.compgraph.request.UnaryInvertibleIntervalFunction;
import experimental.compgraph.tiling.Tile;
import experimental.compgraph.tiling.request.TilingActivator;

public class DefaultOpsTiling<I> extends AbstractInterval implements OpsTiling<I> {

	private final CompgraphOutputNode<OpsTile<I>, ? extends DataHandle<OpsTile<I>, ?>> parent;

	private final int[] tileDims;

	public DefaultOpsTiling(final CompgraphOutputNode<OpsTile<I>, ? extends DataHandle<OpsTile<I>, ?>> parent,
			final long[] gridDims, final int[] tileDims) {
		super(gridDims);
		parent.setOutEdge(this);
		this.parent = parent;
		this.tileDims = tileDims;
	}

	@Override
	public OpsGrid<? extends OpsIterableInterval<OpsTile<I>>> neighbors(final Shape s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <I2> OpsBoundedChannel<? extends Pair<OpsTile<I>, I2>> join(final OpsBoundedChannel<I2> c,
			final BiPredicate<? super OpsTile<I>, ? super I2> f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <I2> OpsBoundedChannel<? extends Pair<OpsTile<I>, I2>> cartesian(final OpsBoundedChannel<I2> c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O> OpsGrid<O> map(final Function<? super OpsTile<I>, O> f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O> OpsGrid<O> map(final BiConsumer<? super OpsTile<I>, ? extends Consumer<O>> f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O> OpsGrid<? extends OpsBoundedChannel<O>> partition(
			final BiConsumer<? super OpsTile<I>, ? extends Consumer<O>> f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <I2> OpsOrderedCollection<? extends Pair<OpsTile<I>, I2>> join(final OpsOrderedCollection<I2> c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OpsCollection<OpsTile<I>> concat(final OpsCollection<OpsTile<I>> c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O> OpsElement<O> reduce(final O memo, final BiFunction<O, ? super OpsTile<I>, O> f,
			final BinaryOperator<O> merge) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OpsElement<OpsTile<I>> treeReduce(final BinaryOperator<OpsTile<I>> f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OpsOrderedCollection<OpsTile<I>> sort(final Comparator<? super OpsTile<I>> f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OpsBoundedStream<OpsTile<I>> stream() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O> OpsCollection<? extends OpsBoundedChannel<O>> group(final Function<? super OpsTile<I>, Integer> f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CompgraphOutputNode<OpsTile<I>, ? extends DataHandle<OpsTile<I>, ?>> parent() {
		return parent;
	}

	@Override
	public Iterator<OpsTile<I>> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RandomAccess<OpsTile<I>> randomAccess() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RandomAccess<OpsTile<I>> randomAccess(final Interval interval) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OpsRai<I> toRai() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O> OpsRai<O> toRai(final Function<? super OpsTiling<I>, ? extends RandomAccessibleInterval<O>> f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O> OpsTiling<O> toTiling(final Converter<? super I, O> c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O> OpsTiling<O> mapTile(final Function<? super OpsTile<I>, ? extends RandomAccessibleInterval<O>> f) {
		// FIXME: hierarchical Ops* vs. DataHandle
		Function<? super OpsTile<I>, OpsTile<O>> fToTile;
		if (f instanceof UnaryInvertibleIntervalFunction) {
			UnaryInvertibleIntervalFunction<? super OpsTile<I>, ? extends RandomAccessibleInterval<O>> tmp =
					(UnaryInvertibleIntervalFunction<? super OpsTile<I>, ? extends RandomAccessibleInterval<O>>) f;
			fToTile = new MyInvertibleFunction<>(tmp);
		} else {
			fToTile = f.andThen((rai) -> new DefaultOpsTile<>(rai));
		}

		final Map<OpsTile<I>, ? extends DataHandle<OpsTile<I>, ?>, OpsTile<O>, ? extends DataHandle<OpsTile<O>, ?>> map = parent
				.cgs().factory().mapTile(this, fToTile);
		final long[] gridDims = new long[n];
		dimensions(gridDims);
		return new DefaultOpsTiling<>(map, gridDims, tileDims);
	}

	public static class MyInvertibleFunction<I, O> extends AbstractUnaryFunctionOp<OpsTile<I>, OpsTile<O>>
			implements UnaryInvertibleIntervalFunction<OpsTile<I>, OpsTile<O>> {

		private UnaryInvertibleIntervalFunction<? super OpsTile<I>, ? extends RandomAccessibleInterval<O>> delegate;

		public MyInvertibleFunction(
				UnaryInvertibleIntervalFunction<? super OpsTile<I>, ? extends RandomAccessibleInterval<O>> delegate) {
			this.delegate = delegate;
		}

		@Override
		public OpsTile<O> compute1(OpsTile<I> input) {
			return new DefaultOpsTile<>(delegate.compute1(input));
		}

		@Override
		public Interval invert(Tile t, TilingActivator activator) {
			return delegate.invert(t, activator);
		}
	}

	@Override
	public OpsTiling<I> interval(final Interval i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OpsTiling<I> subsample(final long... steps) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OpsIterableInterval<OpsTile<I>> filter(final Predicate<? super OpsTile<I>> f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OpsTiling<I> fixOrder() {
		// TODO Auto-generated method stub
		return null;
	}

}
