
package experimental.compgraph;

import net.imglib2.util.Pair;

public abstract class AbstractCompgraphBinaryNode<I1, I2, IN extends DataHandle<Pair<I1, I2>, ?>, O, OUT extends DataHandle<O, ?>>
	extends AbstractCompgraphInnerNode<Pair<I1, I2>, IN, O, OUT> implements CompgraphBinaryNode<I1, I2, IN, O, OUT>
{

	// TODO: maybe this class is not needed at all because we merge double edge inputs to a single edge of pairs.

	public AbstractCompgraphBinaryNode(final CompgraphDoubleEdge<I1, I2> in) {
		super(in);
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
