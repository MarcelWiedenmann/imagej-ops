
package experimental.compgraph;

import net.imglib2.util.Pair;

public abstract class AbstractCompgraphBinaryNode<IN extends DataHandle<Pair<I1, I2>, ?>, I1, I2, O, OUT extends DataHandle<O, ?>>
	extends AbstractCompgraphInnerNode<IN, Pair<I1, I2>, O, OUT> implements CompgraphBinaryNode<IN, I1, I2, O, OUT>
{

	public AbstractCompgraphBinaryNode(final CompgraphDoubleEdge<I1, I2> in, final CompgraphNodeFactory factory) {
		super(in, factory);
		// TODO Auto-generated constructor stub
	}

	// protected abstract OUT applyInternal(IN inData);

	// -- AbstractCompgraphInnerNode --

	@SuppressWarnings("unchecked")
	@Override
	public CompgraphDoubleEdge<I1, I2> in() {
		return (CompgraphDoubleEdge<I1, I2>) super.in();
	}

	// -- CompgraphNode --

	// TODO: Add dataflow() to CompgraphDoubleEdge.
	// @SuppressWarnings("unchecked")
	// @Override
	// public OUT apply() {
	// final IN inData;
	// try {
	// inData = (IN) in().dataflow();
	// }
	// catch (final ClassCastException ex) {
	// throw new UnsupportedOperationException("Wrong input data handle type in compgraph node.", ex);
	// }
	// return applyInternal(inData);
	// }
}
