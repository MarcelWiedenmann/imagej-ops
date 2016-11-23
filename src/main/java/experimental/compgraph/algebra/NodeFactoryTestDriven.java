
package experimental.compgraph.algebra;

import java.util.function.Function;

import experimental.algebra.OpsCollection;
import experimental.compgraph.Edge;
import experimental.compgraph.Node;
import experimental.compgraph.UnaryEdge;

public class NodeFactoryTestDriven {

	// -- Factory Methods --

	public static <I, IN extends Edge<I>, O, OUT extends UnaryEdge<O>> Node<IN, ? extends Map<I, IN, O, OUT>, OUT> map(
		final IN in, final Function<? super I, O> f, final OUT out)
	{
		final TestNode<IN, TestMap<I, IN, O, OUT>, OUT> n = new TestNode<>(in, new TestMap<>(f), out);
		return n;
	}

	// -- Test Implementations --

	public static abstract class TestOpsCollection<I> implements OpsCollection<I> {

		private final Node<?, ? extends Function<?, TestOpsCollection<I>>, TestOpsCollection<I>> source = null;

		@Override
		public <O> OpsCollection<O> map(final Function<? super I, O> f) {

			// TODO: how to get 'out'?
			// (a) pre-allocate 'out' and connect using a node factory method (current approach, simple, generic)
			// (b) let node generate 'out' (more intuitive) --> not generic!: needs some helper (e.g. 'in'-edge could
			// implement a
			// method createFromSource(node) = 'out').
			final TestOpsCollection<O> out = null;

			// TODO: cast f to Function<I,O>? or keep "? super I" throughout the graph building?
			// (refactor generics in general)
			return NodeFactoryTestDriven.map(this, f, out).out();
		}
	}

	public static class TestNode<IN extends Edge<?>, BODY extends Function<IN, OUT>, OUT extends UnaryEdge<?>> implements
		Node<IN, BODY, OUT>
	{

		private final IN in;
		private final BODY body;
		private final OUT out;

		public TestNode(final IN in, final BODY body, final OUT out) {
			this.in = in;
			this.body = body;
			this.out = out;

			// FIXME: fix typing of nodes & edges
			// TODO: how (and where!) to connect nodes and edges? (maybe factory's job...)
			out.setSource(this);
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
	}

	public static class TestMap<I, IN extends Edge<I>, O, OUT extends UnaryEdge<O>> implements Map<I, IN, O, OUT> {

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
