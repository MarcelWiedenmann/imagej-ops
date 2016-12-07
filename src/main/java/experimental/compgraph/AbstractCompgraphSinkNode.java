
package experimental.compgraph;

public abstract class AbstractCompgraphSinkNode<I, IN extends DataHandle<I, ?>, OUT> extends
	AbstractCompgraphInputNode<I, IN> implements CompgraphSinkNode<I, IN, OUT>
{

	public AbstractCompgraphSinkNode(final CompgraphEdge<I> in) {
		super(in);
	}

	protected abstract OUT getInternal(IN inData);

	// -- CompgraphSinkNode --

	@SuppressWarnings("unchecked")
	@Override
	public OUT get() {
		final IN inData;
		try {
			if (in() instanceof CompgraphSingleEdge) {
				inData = (IN) ((CompgraphSingleEdge<I>) in()).dataflow();
			}
			else if (in() instanceof CompgraphDoubleEdge) {
				throw new UnsupportedOperationException("Double edges are not yet supported in compgraph sink nodes.");
			}
			else {
				throw new UnsupportedOperationException("Unsupported edge type in compgraph sink node.");
			}
		}
		catch (final ClassCastException ex) {
			throw new UnsupportedOperationException("Wrong input data handle or edge type in compgraph sink node.", ex);
		}
		return getInternal(inData);
	}
}
