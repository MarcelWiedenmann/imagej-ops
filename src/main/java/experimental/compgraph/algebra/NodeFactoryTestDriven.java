
package experimental.compgraph.algebra;

import java.util.Comparator;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import net.imglib2.util.Pair;

import experimental.algebra.OpsBoundedStream;
import experimental.algebra.OpsCollection;
import experimental.algebra.OpsList;
import experimental.compgraph.Edge;
import experimental.compgraph.Node;
import experimental.compgraph.UnaryEdge;

public class NodeFactoryTestDriven {

	// -- Factory Methods --

	public static <IN extends Edge<I>, I, O, OUT extends UnaryEdge<O>> Node<IN, ? extends Map<IN, I, O, OUT>, OUT> map(
		final IN in, final Function<? super I, O> f)
	{
		final TestNode<IN, TestMap<IN, I, O, OUT>, OUT> n = new TestNode<>(in, new TestMap<>(f));
		return n;
	}

	// -- Test Implementations --

	public static class TestOpsCollection<I> implements OpsCollection<I> {

		private final Node<?, ? extends Function<?, TestOpsCollection<I>>, TestOpsCollection<I>> source;

		public TestOpsCollection(final Node<?, ? extends Function<?, TestOpsCollection<I>>, TestOpsCollection<I>> source) {
			this.source = source;
		}

		@Override
		public <O> TestOpsCollection<O> map(final Function<? super I, O> f) {
			// TODO: cast f to Function<I,O>? or keep "? super I" throughout the graph building?
			// (refactor generics in general)
			final Node<TestOpsCollection<I>, ? extends Map<TestOpsCollection<I>, I, O, TestOpsCollection<O>>, TestOpsCollection<O>> map =
				NodeFactoryTestDriven.map(this, f);
			map.setOutput(new TestOpsCollection<O>(map));
			return map.out();
		}

		@Override
		public Node<?, ? extends Function<?, ? extends UnaryEdge<I>>, ? extends UnaryEdge<I>> source() {
			// TODO Auto-generated method stub
			return null;
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
			// TODO Auto-generated method stub
			return null;
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

	public static class TestNode<IN extends Edge<?>, BODY extends Function<IN, OUT>, OUT extends UnaryEdge<?>> implements
		Node<IN, BODY, OUT>
	{

		private final IN in;
		private final BODY body;
		private OUT out;

		public TestNode(final IN in, final BODY body) {
			this.in = in;
			this.body = body;
		}

		@Override
		public IN in() {
			return in;
		}

		@Override
		public BODY body() {
			return body;
		}

		@Override
		public OUT out() {
			return out;
		}

		@Override
		public void setOutput(final OUT out) {
			this.out = out;
		}
	}

	public static class TestMap<IN extends Edge<I>, I, O, OUT extends UnaryEdge<O>> implements Map<IN, I, O, OUT> {

		private final Function<? super I, O> f;

		public TestMap(final Function<? super I, O> f) {
			this.f = f;
		}

		@Override
		public OUT apply(final IN t) {
			throw new UnsupportedOperationException("nope");
		}

		@Override
		public Function<? super I, O> func() {
			return f;
		}
	}
}
