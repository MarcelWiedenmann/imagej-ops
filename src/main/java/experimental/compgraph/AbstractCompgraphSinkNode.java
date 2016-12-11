
package experimental.compgraph;

public abstract class AbstractCompgraphSinkNode<IO, INOUT extends DataHandle<IO, ?>, OUT> extends
	AbstractCompgraphInputNode<IO, INOUT> implements CompgraphSinkNode<IO, INOUT, OUT>
{

	public AbstractCompgraphSinkNode(final CompgraphEdge<IO> in) {
		super(in);
	}

	protected abstract OUT getInternal(INOUT inData);

	// -- CompgraphSinkNode --

	@SuppressWarnings("unchecked")
	@Override
	public OUT get() {
		final INOUT inData;
		try {
			if (in() instanceof CompgraphSingleEdge) {
				inData = (INOUT) ((CompgraphSingleEdge<IO>) in()).dataflow();
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
