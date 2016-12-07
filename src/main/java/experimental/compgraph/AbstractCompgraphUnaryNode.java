
package experimental.compgraph;

public abstract class AbstractCompgraphUnaryNode<I, IN extends DataHandle<I, ?>, O, OUT extends DataHandle<O, ?>>
	extends AbstractCompgraphInnerNode<I, IN, O, OUT> implements CompgraphUnaryNode<I, IN, O, OUT>
{

	public AbstractCompgraphUnaryNode(final CompgraphSingleEdge<I> in) {
		super(in);
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
			throw new UnsupportedOperationException("Wrong input data handle type in inner compgraph node.", ex);
		}
		return applyInternal(inData);
	}
}
