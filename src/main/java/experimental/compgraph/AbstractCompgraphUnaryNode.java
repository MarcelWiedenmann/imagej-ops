
package experimental.compgraph;

public abstract class AbstractCompgraphUnaryNode<IN extends DataHandle<I, ?>, I, O, OUT extends DataHandle<O, ?>>
	extends AbstractCompgraphInnerNode<IN, I, O, OUT> implements CompgraphUnaryNode<IN, I, O, OUT>
{

	public AbstractCompgraphUnaryNode(final CompgraphSingleEdge<I> in, final CompgraphNodeFactory factory) {
		super(in, factory);
	}

	protected abstract OUT applyInternal(IN inData);

	// -- AbstractCompgraphInnerNode --

	@Override
	public CompgraphSingleEdge<I> in() {
		return (CompgraphSingleEdge<I>) super.in();
	}

	// -- CompgraphNode --

	@SuppressWarnings("unchecked")
	@Override
	public OUT apply() {
		final IN inData;
		try {
			inData = (IN) in().dataflow();
		}
		catch (final ClassCastException ex) {
			throw new UnsupportedOperationException("Wrong input data handle type in compgraph node.", ex);
		}
		return applyInternal(inData);
	}
}
