
package experimental.compgraph;

public abstract class AbstractCompgraphSourceNode<IO, INOUT extends DataHandle<IO, ?>> extends
	AbstractCompgraphOutputNode<IO, INOUT> implements CompgraphSourceNode<IO, INOUT>
{

	private final INOUT inData;

	public AbstractCompgraphSourceNode(final INOUT inData) {
		this.inData = inData;
	}

	// -- CompgraphNode --

	@Override
	public INOUT apply() {
		return inData;
	}
}
