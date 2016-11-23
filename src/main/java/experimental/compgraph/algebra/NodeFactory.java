
package experimental.compgraph.algebra;

import net.imagej.ops.special.function.UnaryFunctionOp;

import experimental.compgraph.Edge;
import experimental.compgraph.Node;

public class NodeFactory {

	public static <I, IN extends Edge<I>, O, OUT extends Edge<O>> Node<IN, Map<I, IN, O, OUT>, OUT> map(final IN in,
		final UnaryFunctionOp<I, O> f)
	{
		final Node<IN, Map<I, IN, O, OUT>, OUT> n = new Node<>(in, new Map<>(f));
		return n;
	}

	public static class DefaultNode<IN extends Edge<?>, BODY extends UnaryFunctionOp<IN, OUT>, OUT extends Edge<?>>
		implements Node<IN, BODY, OUT>
	{

		private final IN in;
		private final BODY body;

		public DefaultNode(final IN in, final BODY body) {
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
			// TODO Auto-generated method stub
			return null;
		}

	}

	public static class DefaultMap {

	}
}
